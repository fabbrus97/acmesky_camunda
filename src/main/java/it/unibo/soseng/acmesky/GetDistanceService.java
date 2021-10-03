package it.unibo.soseng.acmesky;

//import io.swagger.airline.Configuration;
import io.swagger.client.ApiException;
import io.swagger.client.api.RisorseApi;
import io.swagger.client.auth.HttpBasicAuth;
import io.swagger.client.model.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import airline.ApiClient;
import airline.Configuration;

import java.util.ArrayList;

 

public class GetDistanceService {

    public GetDistanceService() {
    }

    public static void service(DelegateExecution execution, boolean findNearestTransport){
    	//Nota: possiamo usare questo servizio per due motivi, sapere qual è la distanza casa utente - aeroporto
    	//oppure sapere qual è la compagina dei trasporti più vicina all'utente. Nel primo caso la variabile
    	//findNearestTransport sarà false, nel secondo true. 

        System.out.println("ACMESKY: cerco le distanze casa utente - aeroporto");
        
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        //defaultClient.setBasePath(((ArrayList<String>)execution.getVariable("geoproviders")).get(0));
        defaultClient.setBasePath(StaticValues.geoproviderUrl);
        
        System.out.println("ACMESKY: url del server geoprovider: " + StaticValues.geoproviderUrl);
        defaultClient.setConnectTimeout(20*1000); 
        
    	RisorseApi apiInstance = new RisorseApi();
    	
    	apiInstance.getApiClient().setBasePath(StaticValues.geoproviderUrl); //TODO this is the way
    	apiInstance.getApiClient().setUsername(StaticValues.distance_username); //TODO this is the way
    	apiInstance.getApiClient().setPassword(StaticValues.distance_password); //TODO this is the way

        if (StaticValues.distance_token == ""){

            Credentials body = new Credentials(); // Credentials
            body.setUsername(StaticValues.distance_username);
            body.setPassword(StaticValues.distance_password);
            try {
                RegisterMaps result = apiInstance.postRegistration(body);
                StaticValues.distance_token = result.getToken();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }

        }

        
        // Configure HTTP basic authorization: authorization
        /*HttpBasicAuth authorization = (HttpBasicAuth) defaultClient.getAuthentication("authorization");
        //authorization.setUsername(StaticValues.distance_username);
        //authorization.setPassword(StaticValues.distance_password);
        //TODO
        if (defaultClient.getAuthentication("authorization") == null) {
        	System.out.println(" ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️ ");
        	System.out.println("authorization è null");
        	System.out.println(" ============================================================================ ");
        } else {
        	System.out.println(" 🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️🆗️ ");
        	System.out.println("authorization non è null");
        	System.out.println(" ============================================================================ ");
        }
        authorization.setUsername("acmesky");
        authorization.setPassword("12345678");
        
        HttpBasicAuth myAuth = new HttpBasicAuth();
        myAuth.setUsername("acmesky");
        myAuth.setPassword("12345678");*/
        /*System.out.println(" 🆗️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️⚠️🆗️ ");
        defaultClient.getAuthentications().forEach((name, auth) -> {
        	System.out.println("Authentication name: " + name);
        	System.out.println("classe del metodo di auth:" + auth.getClass());
        });*/

        /*TODO non ho capito come funziona l'autenticazione
         * 1. sembra che il codice funzioni anche senza auth
         * 2. sembra che dobbiamo usare un token, ma il server sia configurato per httpBasicAuth??? 
         */
        
        GeoBody body2 = new GeoBody(); // InlineObject |
        //body2.setPointA(execution.getVariable("clientAddress").toString());
        String client_home_address = "";
        String departure_airport = "";
        
        String id = ""; 
        if (findNearestTransport) {
	        id = execution.getVariable("acmesky_code").toString();
        } else {
        	id = execution.getVariable("paymLink").toString();
        }
        
        Transazione tr = null;
        
    	System.out.println("Cerco tra le transazioni di acmesky una con id " + id);
    	
        for (Transazione t : StaticValues.transazioni) {
        	if (findNearestTransport) {
	        	if (t.acmesky_offer_code!= null && t.acmesky_offer_code.equals(id)) {
	        		System.out.println("> Trovata");
	        		client_home_address = t.home_address;
	        		departure_airport = t.flight.getDepartureFrom();
	        		tr = t; 
	        	}
        	} else {
        		if (t.paymentLink != null && t.paymentLink.contentEquals(id)) {
	        		System.out.println("> Trovata");
	        		client_home_address = t.home_address;
	        		departure_airport = t.flight.getDepartureFrom();
	        		tr = t; 
	        	}
        	}
        }
    
        
        body2.setPointA(client_home_address);
        ArrayList<String> pointsB = new ArrayList<String>();
        //airport.add(execution.getVariable("airport").toString());
        if (!findNearestTransport) //stiamo cercando un aeroporto; la stringa finale sarà e.g. "BLQ airport"
        	pointsB.add(departure_airport.concat(" airport"));
        else {
        	for (String[] server: StaticValues.transports) //ogni elemento è una coppia <url, indirizzo>
        		pointsB.add(server[1]); 
        }
        body2.setPointsB(pointsB);
        try {	
        	System.out.println("In realtà la richiesta va a :" + apiInstance.getApiClient().getBasePath());
        	if (!findNearestTransport)
        		System.out.println("ACMESKY: cerco la distanza per vedere se l'utente è vicino all'aeroporto; casa - aeroporto: " + client_home_address + " ; " + departure_airport + " - " + tr.flight.getDestination() + " (" + tr.flight.getOfferCode() + ")");
            
        	DistanceResult result = apiInstance.postDistance(body2);
            System.out.println(result);
            if (!findNearestTransport) {
	            int dist = result.getDistance().get(0).getValue().intValue();
	            String unit = result.getDistance().get(0).getUnit();
	            if (unit.equals("m")){
	                dist= (int) dist / 1000;
	            }
	            //System.out.println("Risultato della richiesta delle distanze tra " + execution.getVariable("clientAddress").toString() + " e " + execution.getVariable("airport").toString() + ": " + result);
	
	            execution.setVariable("distance",(int)dist);
	            
	            System.out.println("ACMESKY: ricerca distanze completata, distanze settate: " + dist);
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
            
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postDistance");
            e.printStackTrace();
        }
    }
}
