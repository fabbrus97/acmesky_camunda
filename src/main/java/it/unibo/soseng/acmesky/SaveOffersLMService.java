package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import com.google.gson.Gson;

import io.swagger.client.model.LMflightFlight;
import io.swagger.client.model.Lmflight;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.LMFlight;
import it.unibo.soseng.acmesky.Json.Offers;

public class SaveOffersLMService {
	
	public SaveOffersLMService() {
		
	}
	
	public static void service(String lmoffer) {
		
		//Nota: brutto fix per non rigenerare il server delle airlines e non dover creare una nuova classe per LMFLight
		lmoffer = lmoffer.replace("offer_code", "offerCode");
		lmoffer = lmoffer.replace("departure_from", "departureFrom");
		
		Gson gson = new Gson();
		System.out.println(lmoffer);
		LMFlight lmflight = gson.fromJson(lmoffer, LMFlight.class);
		
		Offers o = GetOffersService.getJSON();
		
		System.out.println("ACMESKY: ho ricevuto l'offerta LM " + lmflight.getFlight().getOfferCode());
		
		ArrayList<Flight> offers; 
		
		if (o.getOffers().containsKey(lmflight.getCompanyname())) {
			offers = o.getOffers().get(lmflight.getCompanyname()); 
		} else {
			offers = new ArrayList<Flight>();
		}
		
		offers.add(lmflight.getFlight());
		o.getOffers().put(lmflight.getCompanyname(), offers);
		
		GetOffersService.saveJSON(o);
	
	}
	
	private static String getCompanyName(String offcode) {
				
		if (offcode.contains("emirates")) {
			return "emirates";
		} else if (offcode.contains("rayanair")) {
			return "rayanair";
		} else if (offcode.contains("britishaw")) {
			return "britishaw";
		} else if (offcode.contains("japanairl")) {
			return "japanairl";
		} 
				
		return null;
		
		
	}
	
}
