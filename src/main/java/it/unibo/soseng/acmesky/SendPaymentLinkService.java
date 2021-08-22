package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.RuntimeService;

public class SendPaymentLinkService {

	public SendPaymentLinkService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String link, String code) {
		
		StaticValues.codes2delete.put(link, code);
		
		runtimeService.createMessageCorrelation("PaymentLink")
		.setVariable("paymentLink", link)
		.setVariable("codeCorrect", true)
		.correlateAll();
		
		
	}
	
}
