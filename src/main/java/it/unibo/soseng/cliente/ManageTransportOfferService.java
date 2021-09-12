package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;

public class ManageTransportOfferService {

	public ManageTransportOfferService() {
		
	}
	
	public static void service(RuntimeService runtimeService, String acmesky_code) {
		boolean accept = (int)(Math.random()*10) >= 1; //c'è una probabilità del 10% di rifiutare il trasporto
		
		if (accept) {
			System.out.println("Cliente: accettiamo il trasporto!");
			runtimeService.createMessageCorrelation("TransportOfferAnswer")
			.setVariable("bookTransport", true)
			.setVariable("acmesky_code", acmesky_code)
			.correlate();
		} else {
			System.out.println("Cliente: rifiutiamo il trasporto");
			runtimeService.createMessageCorrelation("TransportOfferAnswer")
			.setVariable("acmesky_code", acmesky_code)
			.setVariable("bookTransport", false)
			.correlate();
		}
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_code == acmesky_code );
		
	}
}
