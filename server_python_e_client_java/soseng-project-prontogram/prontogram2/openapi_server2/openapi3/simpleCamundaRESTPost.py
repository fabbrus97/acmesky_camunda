import requests, json

def _startMessage(messageName, processInstance, variables=None):
	#invia un messaggio
	post_url = "http://localhost:8080/engine-rest/message"
	#myobj = { "processInstanceId": id, "messageName": "Message_0fnc6rd"}
	myobj = { "processInstanceId": processInstance, "messageName": messageName}
	if variables != None:
		myobj["processVariables"] = variables

	print("=========== DEBUG ===========")
	print("provo a inviare un messaggio a ", messageName, "(instance ", processInstance, ")")

	x = requests.post(post_url, json = myobj, headers={"Content-Type": "application/json"})

	print("Esito messaggio: ", x.text)
	
	return x.status_code

def _intermediateMessage(messageName, processInstance, variables=None):
	print("Invio messaggio a process instance:", processInstance)
	#invia un messaggio
	post_url = "http://localhost:8080/engine-rest/message"
	#myobj = { "processInstanceId": id, "messageName": "Message_0fnc6rd"}
	myobj = { "processInstanceId": processInstance, "messageName": messageName, "all": True}
	if variables != None:
		myobj["processVariables"] = variables
	x = requests.post(post_url, json = myobj, headers={"Content-Type": "application/json"})

	print(x.text)


def sendMessage(messageName, variables=None):
	"""
	variables = {	
		"aVariable" : {"value" : "aNewValue", "type": "String"},
		"anotherVariable" : {"value" : true, "type": "Boolean"}	
	}
	"""

	#cerca il processo che si chiama "acmesky"

	get_url = "http://localhost:8080/engine-rest/deployment"

	response = requests.request("GET", get_url)

	response_j = json.loads(response.text)

	id = ""

	for element in response_j:
		if element["name"] == "acmesky":
			id = element["id"]

	get_url = "http://localhost:8080/engine-rest/process-instance?deploymentId={}".format(id)	
	response = requests.request("GET", get_url)
	response_j = json.loads(response.text)

	status = _startMessage(messageName, id, variables)
	if status == 400:
		for r in response_j:
			_intermediateMessage(messageName, r["id"], variables)

	