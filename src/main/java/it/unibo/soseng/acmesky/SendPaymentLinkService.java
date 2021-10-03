package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.RuntimeService;

public class SendPaymentLinkService {

	public SendPaymentLinkService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String code) {
		
		String link = "";
		
		String username = "";
				
		for(Transazione t : StaticValues.transazioni) {
			System.out.println(t.username + " " + t.paymentLink);
			if (t.acmesky_offer_code.contentEquals(code)) {
				link = t.paymentLink;
				break;
			}
		}
		
		StaticValues.codes2delete.put(link, code);
		System.out.println("ACMESKY: sto per mandare all'utente il link " + link + " per lo username " + username);
		runtimeService.createMessageCorrelation("PaymentLink")
		.setVariable("paymentLink", link)
		.setVariable("code", code)
		.correlateAll();		
		
	}
	
}
