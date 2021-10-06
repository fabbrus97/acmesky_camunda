package it.unibo.soseng.acmesky.Json;

import java.util.ArrayList;
import java.util.HashMap;

import it.unibo.soseng.acmesky.Json.Flight;

public class Offers{

	
    HashMap<String, ArrayList<Flight>> offers; //hashmap di companyName, lista di voli
    

    public Offers() {
    	offers = new HashMap<String, ArrayList<Flight>>();
    }


	public HashMap<String, ArrayList<Flight>> getOffers() {
		return offers;
	}


	public void setOffers(HashMap<String, ArrayList<Flight>> offers) {
		this.offers = offers;
	}

    
    
}