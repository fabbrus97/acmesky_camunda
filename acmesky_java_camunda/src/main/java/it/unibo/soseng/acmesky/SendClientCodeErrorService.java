package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class SendClientCodeErrorService {

	public static void service(DelegateExecution execution) {
		
		String code = execution.getVariable("code2check").toString();
		
		for (Transazione t: StaticValues.transazioni) {
			if (t.acmesky_offer_code.contentEquals(code)) {
				execution.getProcessEngine().getRuntimeService().createMessageCorrelation("")
				.setVariable("username", t.username)
				.setVariable("code", code)
				.correlate();
			}
			break;
		}
		
	}

}
