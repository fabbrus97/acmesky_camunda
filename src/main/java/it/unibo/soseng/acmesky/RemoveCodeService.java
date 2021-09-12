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
		String code2delete = "";
		for (Transazione t: StaticValues.transazioni) {
			if (t.paymentLink.contentEquals(link)) {
				code2delete = t.acmesky_offer_code;
			}
		}
		//String code2delete = //StaticValues.codes2delete.get(link);
		final String code2deletefinal = code2delete;
		Codes codes = GenerateCodesService.deserialize_file();
		
		/*codes.getCodes().forEach( code -> {
			SaveInterestService.deserialize_file().clients.forEach( (name, client) -> {
				if (code.getUser().equals(name)) {
					execution.setVariable("clientAddress", client.getClientAddress());
				}
			});
			if (code.getCode().equals(code2delete)) {
				String flyCode = code.getFly_code();
				GetOffersService.getJSON().getOffers().forEach( (companyName, lista_voli) -> {
					lista_voli.forEach( volo -> {
						if (volo.getOfferCode().equals(flyCode)){
							execution.setVariable("cost", volo.getPrice().getAmount());
							execution.setVariable("airport", volo.getDepartureFrom() + " airport");
						}
					});
				});
			}
		});*/
		codes.getCodes().removeIf( code -> code.getCode().equals(code2deletefinal) );
		GenerateCodesService.serialize_json(codes);	
		

		
	}
}
