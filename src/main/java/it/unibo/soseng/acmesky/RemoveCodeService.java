package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import it.unibo.soseng.acmesky.Json.Client;
import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

public class RemoveCodeService {

	public RemoveCodeService() {
		
	}
	
	public static void service(DelegateExecution execution) {
		String link = execution.getVariable("paymLink").toString();
		
		Transazione trans = null;
		for (Transazione t: StaticValues.transazioni) {
			if (t.paymentLink.contentEquals(link)) {
				trans = t; 
				break;
			}
		}

		final String code2deletefinal = trans.acmesky_offer_code;
		Codes codes = GenerateCodesService.deserialize_file();
		
		
		codes.getCodes().removeIf( code -> code.getCode().equals(code2deletefinal) );
		GenerateCodesService.serialize_json(codes);	
		
		//Setto la variabile cost che serve per il proseguimento del processo 
		execution.setVariable("cost", trans.flight.getPrice().getAmount());
		
		if (trans.flight.getPrice().getAmount() < 1000) {
			RestoreInternalStateService.service(trans.acmesky_offer_code);
		}
		

		
	}
}
