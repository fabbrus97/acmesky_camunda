package it.unibo.soseng.acmesky;

import javax.inject.Inject;	

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.airline.*;
import io.swagger.airline.auth.*;
import io.swagger.client.model.*;
import io.swagger.airline.airline_client.RisorseApi;

public class GetOffers implements JavaDelegate {
	
 
	
	@Override
    public void execute(DelegateExecution execution) throws Exception {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        
        String airline = (String) execution.getVariable("airline");
        defaultClient.setBasePath(airline);
        
        
        //registrati presso la compagnia aerea; quest'operazione fornisce una chiave
        //che va salvata per le richieste successive

        if (Static_Values.airline_token == "") {
        
	        RisorseApi apiInstance = new RisorseApi();
	        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials | 
	        
	        body.setUsername("Mario");
	        Static_Values.airline_username = "Mario";
	        body.setPassword("12345abcd");
	        
	        
	        
	        try {
	            InlineResponse2001 result = apiInstance.postRegistration(body);
	            System.out.println(result);
	            execution.setVariable("offers", result);
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postRegistration");
	            e.printStackTrace();
	        }
        } else {
        	//TODO
        }
        
        /*RisorseApi apiInstance = new RisorseApi(defaultClient);
        // InlineObject1 inlineObject1 = new InlineObject1(); // InlineObject1 | 

        MapsV1Credentials mapsV1Credentials = new MapsV1Credentials();
        mapsV1Credentials.setUsername("Mario");
        mapsV1Credentials.setPassword("12345abcd");


        InlineResponse2001 res = apiInstance.postRegistration(mapsV1Credentials);
        */
                
    }
}
