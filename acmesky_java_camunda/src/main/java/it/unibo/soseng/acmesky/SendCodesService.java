package it.unibo.soseng.acmesky;

import prontogramprovider.*;
import prontogramprovider.auth.*;

import java.util.ArrayList;

import io.swagger.client.model.*;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.Offers;
import prontogramprovider.prontogram_client.DefaultApi;
import prontogramprovider.prontogram_client.RisorseApi;

import prontogramprovider.ApiClient;
import prontogramprovider.ApiException;
import prontogramprovider.Configuration;

public class SendCodesService {
	
	private static String url = "";
	
	public SendCodesService() {
		
	}
	
	public static void service(String server) {
		
		url = server;
				
		if (StaticValues.prontogram_key != "") { 
			sendCodes();
			
		} else {
			//richiesta per registrarsi 
			register();
			//richiesta normale
			sendCodes();
		}
	}
	
	private static void sendCodes() {
		Codes codes = GenerateCodesService.deserialize_file();
		
		InlineResponse2003 token = login();
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(url);
        RisorseApi apiInstance = new RisorseApi();

        if (token != null && token.getToken() != null) {
        	defaultClient.setAccessToken(token.getToken());    		    	
        	        	
        	MessageList body = new MessageList();

        	
        	for(Code code: codes.getCodes()) {
        		
        		if (code.isSent())
        			continue;
        		
        		System.out.println("ACMESKY: invio codice per utente" + code.getCode() + " " + code.getUser() + " " + code.getFly_code());
        		code.setSent(true);
        		
        		MessageItem mi = new MessageItem(); 
        		mi.setReceiver(code.getUser());
        		
        		OfferMessage offer = new OfferMessage();
        		offer.setCode(code.getCode());
        		offer.setDescription(make_description(code.getFly_code())); 
        		mi.setOffer(offer);
        		
        		body.addMessagesItem(mi);
        	}
        	
        	GenerateCodesService.serialize_json(codes);

        	try {
				apiInstance.postCreatemessages(body);

			} catch (ApiException e) {
				e.printStackTrace();
			}
        }
		
		}
	
	private static InlineResponse2003 login() {
		
		ApiClient defaultClient = Configuration.getDefaultApiClient();

		defaultClient.setBasePath(url);
		
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername(StaticValues.prontogram_username);
        basicAuth.setPassword(StaticValues.prontogram_password);
        RisorseApi apiInstance = new RisorseApi();

        try {
        	
            InlineResponse2003 result = apiInstance.postLogin();	
            System.out.println(result);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postLogin");
        
        }
        
        return null;
	}
	
	private static void sendMessage(String offer_code, String receiver) {
		InlineResponse2003 token = login();
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(url);
        RisorseApi apiInstance = new RisorseApi();

        if (token != null && token.getToken() != null) {
        	defaultClient.setAccessToken(token.getToken());
        
	        try {
	        	OfferMessage offer = new OfferMessage();
	        	offer.code(offer_code);
	        	CreatemessageData data = new CreatemessageData();
	        	data.offer(offer); data.receiver(receiver);
	        	Message message = new Message();
	        	message.data(data);
	        	apiInstance.setApiClient(defaultClient);
	            apiInstance.postCreatemessage(message);
	    
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postAllmessage");
	            e.printStackTrace();
	        }
        } 
	}
	
	private static String make_description(String flight_code) {
		
		Offers offers = GetOffersService.getJSON();
		for (ArrayList<Flight> flist: offers.getOffers().values()) {
			for (Flight f: flist) {
				if (f.getOfferCode().contentEquals(flight_code)) {
					return f.getDepartureFrom() + " - " + f.getDestination() + ", " + f.getTakeoff() + ", " + f.getPrice().getAmount() + f.getPrice().getCurrency() + ", " + flight_code; 
				}
			}
		}
		
		return "";
	}
	
	private static void register() {
		
		DefaultApi defaultInstance = new DefaultApi();
		defaultInstance.getApiClient().setBasePath(url);
		
		RegisterBody body = new RegisterBody(); 
		
		body.setUsername(StaticValues.prontogram_username); 
		body.setPassword(StaticValues.prontogram_password); 
		
		try {
			defaultInstance.postRegister(body);
			System.out.println("Richiesta di registrazione per prontogram inviata");
		} catch (ApiException e) {
			//e.printStackTrace();
		}
		
	}
}
