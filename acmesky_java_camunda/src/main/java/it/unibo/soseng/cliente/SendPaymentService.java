package it.unibo.soseng.cliente;


import java.util.Calendar;

import io.swagger.client.*;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.MapsV1Credentials;
import io.swagger.client.model.PaymentDataBody;
import io.swagger.client.model.PaymentRegistration;
import io.swagger.client.model.PaymentdataExpiration;
import io.swagger.client.model.PaymentdataTransaction;
import paymentprovider.ApiClient;
import paymentprovider.ApiException;
import paymentprovider.Configuration;
import paymentprovider.auth.*;
import paymentprovider.payment_client.RisorseApi;
import it.unibo.soseng.cliente.StaticValues;


public class SendPaymentService {

	private static String paymentLink = "";
	private static String provider_url = "";
	
	public SendPaymentService() {
		
	}
	
	public static void service(String payment, String url, String code) {
		//payment è il link/codice al quale dobbiamo pagare - non l'indirizzo del server del servizio -, non
		//so quale dei due sarà - o se sarà una stringa
		
		paymentLink = payment;
		provider_url = url;
		
		Transazione current_trans = null; 
		
		for (Transazione t: StaticValues.transazioni) {
			if (t.acmesky_code != null && t.acmesky_code.contentEquals(code)) {
				current_trans = t; 
				break;
			}
			
		}
		if (current_trans == null) {
			System.out.println("CLIENTE: errore fatale");
			return;
		}
		
		current_trans.payment_link = url;
		Cliente c = StaticValues.clienti.get(current_trans.username);
		
		//if key
		if (c.payment_token == null || c.payment_token.length() == 0) {
			c.payment_token = register(c.payment_username, c.payment_password);
			StaticValues.clienti.put(current_trans.username, c);
		} 
		
		ask_link(c.payment_token);
		
		
	}
	
	private static void ask_link(String token) {
		// chiedi url per far pagare cliente
		
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(provider_url);

        // Configure API key authorization: apikey
        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
        apikey.setApiKey(token);
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //apikey.setApiKeyPrefix("Token");

        RisorseApi apiInstance = new RisorseApi();
        PaymentDataBody body = new PaymentDataBody();
        
        body.cardNumber(123456); 
        body.CVC(123); 
        body.circuit("visa");
        PaymentdataTransaction transaction = new PaymentdataTransaction();
        transaction.setDate(Calendar.getInstance().getTime().toString());
        transaction.setId(paymentLink);
        body.setTransaction(transaction);
        
        PaymentdataExpiration expiration = new PaymentdataExpiration();
		expiration.month(10);
		expiration.setYear(2028);
        body.expiration(expiration);
        
        try {
            apiInstance.postPaymentdata(body);
            
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postPaymentdata");
            e.printStackTrace();
        }
					 
	}
	
	private static String register(String username, String password) {
		RisorseApi apiInstance = new RisorseApi();
		apiInstance.getApiClient().setBasePath(provider_url);
        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |
        body.setUsername(username);
        body.setPassword(password); 
        try { 
            PaymentRegistration result = apiInstance.postRegistration(body);
            System.out.println(result);
            return result.getToken() ;
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postRegistration");
            e.printStackTrace();
        }
        return null;
	}
}
