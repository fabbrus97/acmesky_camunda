package it.unibo.soseng.acmesky;

import java.math.BigDecimal;
import java.util.ArrayList;

import paymentprovider.*;
import paymentprovider.auth.*;
import io.swagger.client.model.*;
import paymentprovider.payment_client.RisorseApi;

import it.unibo.soseng.acmesky.StaticValues;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.Offers;

public class GetLinkService {

	public GetLinkService() {
		
	}
	
	private static String offer_code = "";
	private static String company_name = "";
	
	public static void service(String code) {
		
		offer_code = code;
		String link; 
		//if key
		if (StaticValues.payment_provider_key != "") {
			// chiedi link
			
			link = askLink();
			//return link;
			
		} 
		// else register
		else {
			ApiClient defaultClient = Configuration.getDefaultApiClient();
			defaultClient.setBasePath(StaticValues.paymentUrl);
			
			RisorseApi apiInstance = new RisorseApi();
	        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |
	        body.setUsername(StaticValues.prontogram_username);
	        body.setPassword(StaticValues.prontogram_password);
	        try {
	            PaymentRegistration result = apiInstance.postRegistration(body);
	            System.out.println(result);
	            StaticValues.payment_provider_key = result.getToken() ;
	            
	            link = askLink();
	            
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postRegistration");
	            e.printStackTrace();
	            return;
	        }
		}
		
		Transazione tmp = new Transazione("");
		
		for (Transazione t : StaticValues.transazioni) { 	
			if (t.acmesky_offer_code.contentEquals(offer_code)) {
				tmp = t;
				break;
			}
		}
		
		StaticValues.transazioni.remove(tmp);
		tmp.paymentLink = link;
		StaticValues.transazioni.addFirst(tmp);
		
		System.out.println("ACMESKY: ho aggiunto il link " + link + " per l'utente " + tmp.username + " alla lista delle transazioni attive");
		for(Transazione t : StaticValues.transazioni) {
			System.out.println(t.username + " " + t.paymentLink);
		}
	}
	
	private static String askLink() {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(StaticValues.paymentUrl);
		
        // Configure API key authorization: apikey
        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
        
        apikey.setApiKey(StaticValues.payment_provider_key);
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //apikey.setApiKeyPrefix("Token");

        Flight f = get_flight(offer_code);
        
        RisorseApi apiInstance = new RisorseApi();
        LinkBody body = new LinkBody(); // Body1 |
        body.setAirline(company_name); 	
        LinkAmount amount = new LinkAmount();
        amount.setCurrency("eur");
        amount.setValue(BigDecimal.valueOf(f.getPrice().getAmount()));
        body.setAmount(amount);
        body.setOfferCode(offer_code); 
        try {
            Activelink result = apiInstance.getLink(body);
            return result.getLink();
        } catch (ApiException e) {
            System.err.println("ACMESKY: Exception when calling RisorseApi#getLink");
            e.printStackTrace();
            return null;
        }
	}
	
	private static Flight get_flight(String user_code) {
		for (Transazione transazione : StaticValues.transazioni) {
			if (transazione.acmesky_offer_code.contentEquals(user_code)) {
				company_name = transazione.company_name;
				return transazione.flight;
			}
		}
		
		return null;
	}
	
}
