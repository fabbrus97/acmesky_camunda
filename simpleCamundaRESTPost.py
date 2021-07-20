import requests, json

def sendMessage(messageName, variables=None):
	"""
	variables = {	
		"aVariable" : {"value" : "aNewValue", "type": "String"},
		"anotherVariable" : {"value" : true, "type": "Boolean"}	
	}
	"""

	#cerca il processo che si chiama "acmesky"

	get_url = "http://edgar.cs.unibo.it:8080/engine-rest/deployment"

	response = requests.request("GET", get_url)

	id = ""

	response_j = json.loads(response.text)


	for element in response_j:
		if element["name"] == "acmesky":
			id = element["id"]
		

	#invia un messaggio
	
	post_url = "http://edgar.cs.unibo.it:8080/engine-rest/message"
	#myobj = { "processInstanceId": id, "messageName": "Message_0fnc6rd"}
	myobj = { "processInstanceId": id, "messageName": messageName}
	if variables != None:
		myobj = { "processInstanceId": id, "messageName": messageName, "processVariables": variables}
	x = requests.post(post_url, json = myobj, headers={"Content-Type": "application/json"})

	print(x.text)
	
