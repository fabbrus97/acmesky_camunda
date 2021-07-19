package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.RuntimeService;

public class SendPaymentLinkService {

	public SendPaymentLinkService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String link) {
		
		runtimeService.createMessageCorrelation("PaymentLink")
		.setVariable("paymentLink", link)
		.correlate();
		
		
	}
	
}
