package it.unibo.soseng.acmesky;

import prontogramprovider.*;
import prontogramprovider.auth.*;


import io.swagger.client.model.*;
import it.unibo.soseng.acmesky.Json.Codes;
import prontogramprovider.prontogram_client.DefaultApi;
import prontogramprovider.prontogram_client.RisorseApi;

import prontogramprovider.ApiClient;
import prontogramprovider.ApiException;
import prontogramprovider.Configuration;

public class SendCodeService {
	
	private static String url = "";
	
	public SendCodeService() {
		
	}
	
	public static void service(String server) {
		
		url = server;
				
		if (StaticValues.prontogram_key != "") { 
			System.out.println("Abbiamo una api key per prontogram, provo a inviare i codici");
			//TODO cosa succede se abbiamo il token ma è scaduto?
			sendCodes();
			
		} else {
			System.out.println("Non abbiamo una api key per prontogram, provo a registrarmi");
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

        	
        	
        	codes.getCodes().forEach(code -> {
        		MessageItem mi = new MessageItem(); 
        		mi.setReceiver(code.getUser());
        		
        		OfferMessage offer = new OfferMessage();
        		offer.setCode(code.getCode());
        		offer.setDescription(code.getFly_code()); //TODO crea descrizione con volo andata, ritorno, costo e data
        		mi.setOffer(offer);
        		
        		body.addMessagesItem(mi);
        	});
        	
        	
        	try {
        		System.out.println(" ACMESKY SendCodeService: provo a inviare dei messaggi; i messaggi sono " + body.getMessages().size());
				apiInstance.postCreatemessages(body);
				System.out.println("Invio avvenuto correttamente");
			} catch (ApiException e) {
				// TODO Auto-generated catch block	
				//e.printStackTrace();
				System.out.println("Eccezione mandando codice a prontogram da acmesky");
			}
        }
		
		/*codes.getCodes().forEach(code -> {
			sendMessage(code.getCode(), code.getUser());
		});*/
	
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
            e.printStackTrace();
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
	            //TODO come capire se il token è scaduto?
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postAllmessage");
	            e.printStackTrace();
	        }
        } else {
        	System.out.println("Errore di autenticazione col server di prontogram, messaggio non inviato");
        }
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
			// TODO Auto-generated catch block
			System.out.println("Eccezione registrandosi per prontogram");
			e.printStackTrace();
		}
		
	}
}
