package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class AskClientTransportService {

	public AskClientTransportService() {
		
	}

	public static void service(DelegateExecution execution) {
		
		System.out.println("ACMESKY: chiedo al cliente se vuole la navetta");
		
		String code = "";
		
		for(Transazione t: StaticValues.transazioni) {
			if (t.paymentLink != null && t.paymentLink.equals(execution.getVariable("paymLink").toString())) {
				code = t.acmesky_offer_code;
			}
		}
		
		execution.getProcessEngine()
			.getRuntimeService()
			.createMessageCorrelation("getTransportOffer")
			.setVariable("acmesky_code", code)
			.correlate();
		
		
	}
	
}
