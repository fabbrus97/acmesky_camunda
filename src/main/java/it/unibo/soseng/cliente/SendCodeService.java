package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import io.swagger.client.model.OfferMessage;

public class SendCodeService {

	public SendCodeService() {
		
	}
	
	static void service(RuntimeService runtimeService, DelegateExecution execution) {
		
		String code  = execution.getVariable("code").toString(); //questa variabile è impostata da acmesky quando manda il messaggio
		String username = execution.getVariable("username").toString(); //questa variabile è impostata da acmesky quando manda il messaggio
		int contatore = 0;
		for(Transazione t: StaticValues.transazioni) {
			if (t.username.contentEquals(username)) {
				t.acmesky_code = code;
				StaticValues.transazioni.remove(contatore);
				StaticValues.transazioni.add(contatore, t);
				break;
			}
			contatore++;
		}
		
		
		
		runtimeService.createMessageCorrelation("GetCode")
		.setVariable("code2check", code)
		.correlateAll();	
	}
	
}