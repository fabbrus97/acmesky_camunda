package it.unibo.soseng.cliente;


import java.util.Calendar;

import io.swagger.client.*;
import io.swagger.client.model.Body;
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
	
	public static void service(String payment, String url) {
		//FIXME url è il link/codice al quale dobbiamo pagare, non
		//so quale dei due sarà - o se sarà una stringa
		
		//TODO: se per acmesky questo approccio va più che bene, per "cliente"
		//è meno ideale, perché così tutti i clienti condividono la stessa chiave 
		//per l'api; StaticValues.payment_provider_key dovrebbe essere una hashmap ed essere
		//usato tipo così: StaticValues.payment_provider_key["mario rossi"]
		
		paymentLink = payment;
		provider_url = url;
		
		//if key
		if (StaticValues.payment_provider_key.isEmpty() || StaticValues.payment_provider_key == null || StaticValues.payment_provider_key.length() == 0) {
			System.out.println("Mi registro");
			register();
		} 
		System.out.println("Sono già registrato al servizio di pagamento, la mia chiave è: " + StaticValues.payment_provider_key.toString() + " (" + StaticValues.payment_provider_key.length() + ")");
		ask_link();
		
		
	}
	
	private static void ask_link() {
		// chiedi url per far pagare cliente
		
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(provider_url);

        // Configure API key authorization: apikey
        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("apikey");
        apikey.setApiKey(StaticValues.payment_provider_key);
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
            System.out.println("Provo a pagare al link " + paymentLink);
            apiInstance.postPaymentdata(body);
            System.out.println("Pagamento riuscito");
            
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postPaymentdata");
            //e.printStackTrace();
        }
					
	}
	
	private static void register() {
		RisorseApi apiInstance = new RisorseApi();
		apiInstance.getApiClient().setBasePath(provider_url);
        MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |
        body.setUsername("mariorossi"); //TODO
        body.setPassword("123456789"); //TODO
        System.out.println("Sto per registrarmi al servizio di pagamento");
        try {
            PaymentRegistration result = apiInstance.postRegistration(body);
            System.out.println(result);
            StaticValues.payment_provider_key = result.getToken() ;
            System.out.println("Registrazione effettuata");
        } catch (ApiException e) {
            System.err.println("Exception when calling RisorseApi#postRegistration");
            e.printStackTrace();
        }
	}
}
