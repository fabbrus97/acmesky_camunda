package it.unibo.soseng.acmesky;

import java.math.BigDecimal;
import java.util.ArrayList;

import io.swagger.client.model.ActiveLink;
import io.swagger.client.model.Body;
import io.swagger.client.model.LinkAmount;
import io.swagger.client.model.LinkBody;
import io.swagger.client.model.MapsV1Credentials;
import io.swagger.client.model.PaymentRegistration;
import paymentprovider.ApiClient;
import paymentprovider.ApiException;
import paymentprovider.Configuration;
import paymentprovider.auth.ApiKeyAuth; 
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
	
	//public static String service(String code) {
	public static void service() {
		offer_code = StaticValues.transazioni.getFirst().acmesky_offer_code;
		//offer_code = code;
		
		//if key
		if (StaticValues.payment_provider_key != "") {
			// chiedi link
			
			String link = askLink();
			//return link;
	
			Transazione t = StaticValues.transazioni.getFirst(); 
			StaticValues.transazioni.removeFirst();
			t.paymentLink = link;
			StaticValues.transazioni.addFirst(t);
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
	            
	            askLink();
	            return;
	            
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postRegistration");
	            e.printStackTrace();
	            return;
	        }
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
        //amount.setCurrency(f.getPrice().getCurrency());
        amount.setCurrency("eur");
        amount.setValue(BigDecimal.valueOf(f.getPrice().getAmount()));
        body.setAmount(amount);
        body.setOfferCode(offer_code); 
        try {
            ActiveLink result = apiInstance.getLink(body);
            System.out.println(result);
            return result.getLink();
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#getLink");
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
		
		/*
		Codes c = GenerateCodesService.deserialize_file();
		String flight_code = "";
		for (Code code : c.getCodes()) {
			if (code.getCode().equals(user_code)){
				flight_code = code.getFly_code();
				break;
			}
		}
		
		Offers o = GetOffersService.getJSON();
		
		for (String company: o.getOffers().keySet()) {
			for (Flight flight: o.getOffers().get(company)) {
				if (flight.getOfferCode().equals(flight_code)) {
					company_name = company;
					return flight;
				}
			}
			
		}*/
		
		return null;
	}
	
}
