package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;

public class ManageTransportOfferService {

	public ManageTransportOfferService() {
		
	}
	
	public static void service(RuntimeService runtimeService) {
		boolean accept = (int)(Math.random()*10) >= 4; //c'è una probabilità del 40% di rifiutare il trasporto
		
		if (accept) {
			runtimeService.createMessageCorrelation("ClientTransport")
			.correlate();
		} else {
			runtimeService.createMessageCorrelation("ClientNotTransport")
			.correlate();
		}
	}
}
