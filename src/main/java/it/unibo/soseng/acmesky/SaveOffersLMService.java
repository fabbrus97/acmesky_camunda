package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import io.swagger.client.model.LMflightFlight;
import io.swagger.client.model.Lmflight;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.Offers;

public class SaveOffersLMService {
	
	public SaveOffersLMService() {
		
	}
	
	public static void service(String partenza, String data) {
		
		/*
		Offers o = GetOffersService.getJSON();
		
		Flight LMOffer = new Flight();
		//TODO LMOffer.setPrice()...
		
		
		o.getOffers().put("ryanair", o.getOffers().get("ryanair").add(LMOffer));
		
		GetOffersService.saveJSON(o);
		*/
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
}
