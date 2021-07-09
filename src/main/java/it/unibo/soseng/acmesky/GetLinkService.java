package it.unibo.soseng.acmesky;

import java.math.BigDecimal;

import io.swagger.client.model.Body1;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.LinkAmount;
import io.swagger.client.model.MapsV1Credentials;
import io.swagger.paymentprovider.ApiClient;
import io.swagger.paymentprovider.ApiException;
import io.swagger.paymentprovider.Configuration;
import io.swagger.paymentprovider.auth.ApiKeyAuth;
import io.swagger.paymentprovider.payment_client.RisorseApi;

public class GetLinkService {

	public GetLinkService() {
		
	}
	
	public static void service() {
		//if key
		if (Static_Values.payment_provider_key != "") {
			// chiedi link
			
			ApiClient defaultClient = Configuration.getDefaultApiClient();

	        // Configure API key authorization: apikey
	        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
	        apikey.setApiKey("YOUR API KEY");
	        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
	        //apikey.setApiKeyPrefix("Token");

	        RisorseApi apiInstance = new RisorseApi();
	        Body1 body = new Body1(); // Body1 |
	        body.setAirline(""); //TODO
	        LinkAmount amount = new LinkAmount();
	        amount.setCurrency(""); //TODO
	        amount.setValue(BigDecimal.valueOf(0)); //TODO
	        body.setAmount(amount);
	        body.setOfferCode(""); //TODO
	        try {
	            InlineResponse200 result = apiInstance.getLink(body);
	            System.out.println(result);
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#getLink");
	            e.printStackTrace();
	        }
			
		} 
		// else register
		else {
			RisorseApi apiInstance = new RisorseApi();
	        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials | 
	        try {
	            InlineResponse2001 result = apiInstance.postRegistration(body);
	            System.out.println(result);
	            Static_Values.payment_provider_key = result.getToken() ;
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postRegistration");
	            e.printStackTrace();
	        }
		}
	}
	
}
