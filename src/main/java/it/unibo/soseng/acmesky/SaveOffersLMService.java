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
		
		Gson gson = new Gson();
		LMFlight lmflight = gson.fromJson(lmoffer, LMFlight.class);
		
		Offers o = GetOffersService.getJSON();
		
		Flight lmOffer = new Flight();
		lmOffer.setDepartureFrom(lmflight.getPart());
		lmOffer.setDestination(lmflight.getDest());
		lmOffer.setOfferCode(lmflight.getOffc());
		String price = lmflight.getPric(); 
		String currency = price.substring(price.length()-1);
		int amount = Integer.valueOf(price.substring(price.length()-1));
		lmOffer.setPrice(currency, amount);
		lmOffer.setTakeoff(lmflight.getData()); //TODO è da parsare? no se gian modifica main.py
		
		String companyName = getCompanyName(lmflight.getOffc());
		
		o.getOffers().get(companyName).add(lmOffer);
		o.getOffers().put(companyName, o.getOffers().get(companyName));
		
		GetOffersService.saveJSON(o);
	
		//TODO dovrebbe farlo gian...?
		/*for (Lmflight lmflight : lmflights) {

		
			Flight flight = new Flight();
			LMflightFlight f = lmflight.getFlight();
			flight.setDepartureFrom(f.getDepartureFrom());
			flight.setDestination(f.getDestination());
			flight.setOfferCode(f.getOfferCode());
			flight.setPrice(f.getPrice().getCurrency(), f.getPrice().getAmount().intValue());
			flight.setTakeoff(f.getTakeoff());
			o.getOffers().get(lmflight.getCompanyname()).add(flight);
		}*/
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
		} else return null;
		
		
	}
	
}
