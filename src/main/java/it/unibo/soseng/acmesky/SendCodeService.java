package it.unibo.soseng.acmesky;

import prontogramprovider.*;
import prontogramprovider.auth.*;

import java.time.format.DateTimeFormatter;

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
			
			//TODO cosa succede se abbiamo il token ma è scaduto?
			
			Codes codes = GenerateCodesService.deserialize_file();
			codes.getCodes().forEach(code -> {
				code.getUsers().forEach(user -> {
					sendMessage(code.getCode(), user);
				});
				
			});
			
			
			
			
		} else {
			//richiesta per registrarsi 
			register();
			//richiesta normale
			service(server);
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
            e.printStackTrace();
        }
        
        return null;
	}
	
	private static void sendMessage(String offer_code, String receiver) {
		InlineResponse2003 token = login();
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(url);
        RisorseApi apiInstance = new RisorseApi();

        
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
	}
	
	private static void register() {
		
		DefaultApi defaultInstance = new DefaultApi();
		defaultInstance.getApiClient().setBasePath(url);
		
		RegisterBody body = new RegisterBody(); 
		
		body.setUsername(StaticValues.prontogram_username); 
		body.setPassword(StaticValues.prontogram_password); 
		
		try {
			defaultInstance.postRegister(body);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
