package it.unibo.soseng.acmesky;

import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

import java.util.ArrayList;

import it.unibo.soseng.acmesky.Transazione; 

public class CheckCodeService {
	
	public CheckCodeService() {
		
	}
	
	public static boolean service(String code) {
		
		Codes codes = GenerateCodesService.deserialize_file();
		
		for (Code c: codes.getCodes()) {
			if (c.getCode().equals(code)) {
				//crea nuova transazione associata all'utente - vedi Transazione.java
				Transazione t = new Transazione(c.getUser());
				SaveInterestService.deserialize_file().getClients().forEach( (username, client) -> {
					if (c.getUser().equals(username)) {
						//associa indirizzo di casa dell'utente alla transazione
						t.home_address = client.getClientAddress();
					}
				});
				
				//associa codice acmesky alla transazione
				
				t.acmesky_offer_code = c.getCode();
				
				//associa volo alla transazione
				String flight_code = c.getFly_code();
				GetOffersService.getJSON().getOffers().forEach((companyName, flights) -> {
					t.company_name = companyName;
					flights.forEach(flight -> {
						if (flight.getOfferCode().equals(flight_code)) {
							t.flight = flight;
						}
					});
				});
				
				//salva transazione nella coda delle transazioni
				
				StaticValues.transazioni.add(t);
				
				return true;
			}
		}
		
		return false;
		
	}

}
