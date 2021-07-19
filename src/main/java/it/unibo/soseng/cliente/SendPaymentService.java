package it.unibo.soseng.cliente;


import io.swagger.client.*;
import io.swagger.client.model.*;
import io.swagger.paymentprovider.ApiClient;
import io.swagger.paymentprovider.ApiException;
import io.swagger.paymentprovider.Configuration;
import io.swagger.paymentprovider.auth.ApiKeyAuth;
import io.swagger.paymentprovider.payment_client.RisorseApi;
import it.unibo.soseng.cliente.StaticValues;


public class SendPaymentService {

	public SendPaymentService() {
		
	}
	
	public static void service(String url) {
		//FIXME url è il link/codice al quale dobbiamo pagare, non
		//so quale dei due sarà - o se sarà una stringa
		
		//TODO: se per acmesky questo approccio va più che bene, per "cliente"
		//è meno ideale, perché così tutti i clienti condividono la stessa chiave 
		//per l'api; StaticValues.payment_provider_key dovrebbe essere una hashmap ed essere
		//usato tipo così: StaticValues.payment_provider_key["mario rossi"]
		
		
		//if key
		if (StaticValues.payment_provider_key != "") {
			// paga
			
			ApiClient defaultClient = Configuration.getDefaultApiClient();

	        // Configure API key authorization: apikey
	        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
	        apikey.setApiKey(StaticValues.payment_provider_key);
	        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
	        //apikey.setApiKeyPrefix("Token");

	        RisorseApi apiInstance = new RisorseApi();
	        Body body = new Body();
	        body.cardNumber(1234); //TODO
	        body.CVC(123); //TODO
	        body.circuit("CreditCardCompany"); //TODO
	        PaymentdataExpiration expiration = new PaymentdataExpiration();
    		expiration.month(10);
    		expiration.setYear(2028);
	        body.expiration(expiration);
	        
	        
	        try {
	            
	            apiInstance.postPaymentdata(body);
	            
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
	            StaticValues.payment_provider_key = result.getToken() ;
	        } catch (ApiException e) {
	            System.err.println("Exception when calling RisorseApi#postRegistration");
	            e.printStackTrace();
	        }
		}
		
		
	}
}
