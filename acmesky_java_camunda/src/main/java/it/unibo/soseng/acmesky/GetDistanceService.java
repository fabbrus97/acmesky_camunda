package it.unibo.soseng.acmesky;

//import io.swagger.airline.Configuration;
import io.swagger.client.model.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.ArrayList;

 

public class GetDistanceService {

    public GetDistanceService() {
    }

    public static void service(DelegateExecution execution, boolean findNearestTransport){
    	//Nota: possiamo usare questo servizio per due motivi, sapere qual è la distanza casa utente - aeroporto
    	//oppure sapere qual è la compagina dei trasporti più vicina all'utente. Nel primo caso la variabile
    	//findNearestTransport sarà false, nel secondo true. 
        
        geoprovider.geo_client.RisorseApi apiInstance = new geoprovider.geo_client.RisorseApi();
    	
    	apiInstance.getApiClient().setBasePath(StaticValues.geoproviderUrl); 
    	apiInstance.getApiClient().setUsername(StaticValues.distance_username); 
    	apiInstance.getApiClient().setPassword(StaticValues.distance_password); 
    	apiInstance.getApiClient().setConnectTimeout(20*1000);

        if (StaticValues.distance_token == ""){

            Credentials body = new Credentials(); // Credentials
            body.setUsername(StaticValues.distance_username);
            body.setPassword(StaticValues.distance_password);
            try {
                RegisterMaps result = apiInstance.postRegistration(body);
                StaticValues.distance_token = result.getToken();
                System.out.println(result);
            } catch (Exception e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }

        }

        
        GeoBody body2 = new GeoBody(); 
        String client_home_address = "";
        String departure_airport = "";
        
        String id = ""; 
        if (findNearestTransport) {
	        id = execution.getVariable("acmesky_code").toString();
        } else {
        	id = execution.getVariable("paymLink").toString();
        }
        
        Transazione tr = null;    	
        for (Transazione t : StaticValues.transazioni) {
        	if (findNearestTransport) {
	        	if (t.acmesky_offer_code!= null && t.acmesky_offer_code.equals(id)) {
	        		client_home_address = t.home_address;
	        		departure_airport = t.flight.getDepartureFrom();
	        		tr = t; 
	        	}
        	} else {
        		if (t.paymentLink != null && t.paymentLink.contentEquals(id)) {
	        		client_home_address = t.home_address;
	        		departure_airport = t.flight.getDepartureFrom();
	        		tr = t; 
	        	}
        	}
        }
    
        
        body2.setPointA(client_home_address);
        ArrayList<String> pointsB = new ArrayList<String>();
        if (!findNearestTransport) //stiamo cercando un aeroporto; la stringa finale sarà e.g. "BLQ airport"
        	pointsB.add(departure_airport.concat(" airport"));
        else {
        	for (String[] server: StaticValues.transports) //ogni elemento è una coppia <url, indirizzo>
        		pointsB.add(server[1]); 
        }
        body2.setPointsB(pointsB);
        try {	
        	if (!findNearestTransport)
        		System.out.println("ACMESKY: cerco la distanza per vedere se l'utente è vicino all'aeroporto; casa - aeroporto: " + client_home_address + " ; " + departure_airport + " - " + tr.flight.getDestination() + " (" + tr.flight.getOfferCode() + ")");
            
        	DistanceResult result = apiInstance.postDistance(body2);
            if (!findNearestTransport) {
	            int dist = result.getDistance().get(0).getValue().intValue();
	            String unit = result.getDistance().get(0).getUnit();
	            if (unit.equals("m")){
	                dist= (int) dist / 1000;
	            }
	
	            execution.setVariable("distance",(int)dist);
	            
            } else {
            	int least_distant = 0; // indice del servizio di trasporti meno distante
            	int value = result.getDistance().get(0).getValue().intValue();
            	int index = 0;
            	for (DistanceResultDistance res: result.getDistance()) {
            		if (res.getValue().intValue() < value) {
            			least_distant = index;
            			value = res.getValue().intValue();
            		}
            		index++;
            	}
            	execution.setVariable("transportCompanyUrl", StaticValues.transports.get(least_distant)[0]);
            }
            
        } catch (Exception e) {
            System.err.println("Exception when calling RisorseApi#postDistance");
            e.printStackTrace();
        }
    }
}
