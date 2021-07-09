//docs: https://github.com/camunda/camunda-external-task-client-js

const { Client, logger } = require("camunda-external-task-client-js");

url = "http://arianna.cs.unibo.it:8080"
const config = {baseUrl: url, use:logger};

const client = new Client(config);

topicName = ""
client.subscribe("topicName", async function({task, taslService}){
  //do something
  //e.g. const processVariables = new Variables(); processVariables.set(...)

  await taskService.complete(task /*, processVariables*/);
})
