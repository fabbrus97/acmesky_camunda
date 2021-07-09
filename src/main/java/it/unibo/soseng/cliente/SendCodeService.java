package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;

public class SendCodeService {

	public SendCodeService() {
		
	}
	
	static void service(RuntimeService runtimeService, String code) {
		runtimeService.createMessageCorrelation("GetCode")
		.setVariable("code2check", code)
		.correlate();
	}
	
}