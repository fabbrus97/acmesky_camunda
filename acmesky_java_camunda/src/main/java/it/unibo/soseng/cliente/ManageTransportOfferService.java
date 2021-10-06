package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;

public class ManageTransportOfferService {

	public ManageTransportOfferService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String acmesky_code) {
		boolean accept = (int)(Math.random()*10) >= 1; //c'è una probabilità del 10% di rifiutare il trasporto 
		
		String tmp = "";
		for(Transazione t: StaticValues.transazioni) {
			if (t.acmesky_code != null && t.acmesky_code.contentEquals(acmesky_code)) {
				tmp = t.payment_link;
				break;
			}
		}
		
		final String paym_link = tmp;
		
		if (accept) {
			System.out.println("Cliente: accettiamo il trasporto!");
			runtimeService.createMessageCorrelation("TransportOfferAnswer")
			.setVariable("bookTransport", true)
			.setVariable("acmesky_code", acmesky_code) 
			.setVariable("paymLink", paym_link)
			.correlate();
		} else {
			System.out.println("Cliente: rifiutiamo il trasporto");
			runtimeService.createMessageCorrelation("TransportOfferAnswer")
			.setVariable("acmesky_code", acmesky_code)
			.setVariable("paymLink", paym_link)
			.setVariable("bookTransport", false)
			.correlate();
		}
				
	}
}
