package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import io.swagger.client.model.OfferMessage;

public class SendCodeService {

	public SendCodeService() {
		
	}
	
	static void service(RuntimeService runtimeService, DelegateExecution execution) {
		
		String code  = execution.getVariable("code").toString(); //questa variabile è impostata da acmesky quando manda il messaggio
		System.out.println("CLIENTE: invio codice " + code);
		String username = execution.getVariable("username").toString(); //questa variabile è impostata da acmesky quando manda il messaggio
		int contatore = 0;
		for(Transazione t: StaticValues.transazioni) {
			System.out.println("CLIENTE: check transazioni in invio codice cliente: " + t.username);
			if (t.username.contentEquals(username)) {
				//Possono avvenire due casi quando ricevo un codice:
				// trovo una transazione col nome che mi ha inviato acmesky, ma senza nessun codice associato
				// oppure trovo una transazione col nome che mi ha inviato acmesky, che ha già un codice associato
				//Questo può accadere perché Acmesky può trovare più offerte compatibili con un interesse
				//e nel secondo caso basta aggiungere una nuova transazione
				if (t.acmesky_code == null || t.acmesky_code.isEmpty()) {
					t.acmesky_code = code;
					StaticValues.transazioni.remove(contatore);
					StaticValues.transazioni.add(contatore, t);
				} else {
					Transazione e = new Transazione(username);
					e.home_address = t.home_address;
					e.acmesky_code = code;
					StaticValues.transazioni.add(e);
				}
				break;
			}
			contatore++;
		}
		
		
		
		runtimeService.createMessageCorrelation("GetCode")
		.setVariable("code2check", code)
		.correlateAll();	
	}
	
}