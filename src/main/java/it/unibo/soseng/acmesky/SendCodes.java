package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.prontogramprovider.*;
import io.swagger.prontogramprovider.auth.*;
import io.swagger.client.model.*;
import io.swagger.prontogramprovider.prontogram_client.RisorseApi;


public class SendCodes implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//TODO ottieni codice da inviare all'utente e nome utente
		
		if (Static_Values.prontogram_key != "" || true) { //TODO
			
			InlineResponse2003 token = login();			
			//TODO nuovo login
			
			ApiClient defaultClient = Configuration.getDefaultApiClient();

			InlineResponse200 result; 
			
	        RisorseApi apiInstance = new RisorseApi();
	        
	        defaultClient.setAccessToken(token.getToken());
	        
	        try {
	        	InlineObject body = new InlineObject();
	        	defaultClient.setAccessToken(""); //TODO 
	        	body.setOfferCode(""); //TODO
	        	NotifypaymentCustomer customer = new NotifypaymentCustomer();
	        	customer.setName(""); //TODO
	        	body.setCustomer(customer);
	            apiInstance.postCreatemessage(body);
	            
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postAllmessage");
	            e.printStackTrace();
	        }
			
			
		} else {
			//TODO richiesta per registrarsi - FIXME manca proprio su prontogram, forse l'hai fatta in locale
			
			//TODO richiesta normale - createMessage
			
		}
				
		
	}
	
	InlineResponse2003 login() {
		
		RisorseApi apiInstance = new RisorseApi();
        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials | 
        body.setUsername(Static_Values.prontogram_username);
        body.setPassword(Static_Values.prontogram_password);
        try {
            InlineResponse2003 result = apiInstance.postLogin(body);
            System.out.println(result);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postLogin");
            e.printStackTrace();
        }
        
        return null;
	}

}
