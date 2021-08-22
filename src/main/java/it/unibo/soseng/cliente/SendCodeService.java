package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import io.swagger.client.model.OfferMessage;

public class SendCodeService {

	public SendCodeService() {
		
	}
	
	static void service(RuntimeService runtimeService, String code) {
		runtimeService.createMessageCorrelation("GetCode")
		.setVariable("code2check", code)
		.correlateAll();	
	}
	
}