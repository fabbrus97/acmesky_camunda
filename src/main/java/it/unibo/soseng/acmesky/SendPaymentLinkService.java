package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.RuntimeService;

public class SendPaymentLinkService {

	public SendPaymentLinkService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String link, String code) {
		
		StaticValues.codes2delete.put(link, code);
		
		String username = "";
		for(Transazione t : StaticValues.transazioni) {
			if (t.acmesky_offer_code.contentEquals(code)) {
				username = t.username;
				break;
			}
		}
		
		runtimeService.createMessageCorrelation("PaymentLink")
		.setVariable("paymentLink", link)
		.setVariable("username", username)
		.setVariable("codeCorrect", true)
		.correlateAll();
		
		
	}
	
}
