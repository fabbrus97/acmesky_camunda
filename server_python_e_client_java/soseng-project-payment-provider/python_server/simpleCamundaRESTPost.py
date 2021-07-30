import requests, json

def sendMessage(messageName, variables=None):
	"""
	variables = {	
		"aVariable" : {"value" : "aNewValue", "type": "String"},
		"anotherVariable" : {"value" : true, "type": "Boolean"}	
	}
	"""

	#cerca il processo che si chiama "acmesky"

	get_url = "http://arianna.cs.unibo.it:8080/engine-rest/deployment"

	response = requests.request("GET", get_url)

	processId = ""

	response_j = json.loads(response.text)

	for element in response_j:
		if element["name"] == "acmesky":
			processId = element["id"]
	

	#invia un messaggio
	
	post_url = "http://arianna.cs.unibo.it:8080/engine-rest/message"
	#myobj = { "processInstanceId": id, "messageName": "Message_0fnc6rd"}
	myobj = { "processInstanceId": processId, "messageName": messageName}
	if variables != None:
		myobj["processVariables"] = variables
	x = requests.post(post_url, json = myobj, headers={"Content-Type": "application/json"})

	print(x.text)
	
