package it.unibo.soseng.acmesky;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import airline.ApiClient;
import airline.airline_client.RisorseApi;
import io.swagger.client.model.*;
import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Offers;
import it.unibo.soseng.acmesky.Json.Flight;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;



public class GetOffersService {

    public GetOffersService(){

    }

    public static void service(String airline){

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(airline);

        if (StaticValues.airline_token == ""){
            RisorseApi apiInstance = new RisorseApi();
            MapsV1Credentials body = new MapsV1Credentials(); // MapsV1Credentials |

            body.setUsername(StaticValues.airline_username);
            body.setPassword(StaticValues.airline_password);

            try {
                InlineResponse2001 result = apiInstance.postRegistration(body);
                System.out.println(result);
                StaticValues.airline_token = result.getToken();
            } catch (airline.ApiException e) {
                System.err.println("Exception when calling RisorseApi#postRegistration");
                e.printStackTrace();
            }
        }

        RisorseApi apiInstance = new RisorseApi();
        try {
            InlineResponse200 result = apiInstance.getFlights();
            Offers offers = new Offers();
            System.out.println(result);
            ArrayList <InlineResponse200Flights> flights = (ArrayList<InlineResponse200Flights>) result.getFlights();
            ArrayList<Flight> voli = new ArrayList<Flight>();
            if(flights != null){
            	
                for (InlineResponse200Flights flight : flights) {
                    Flight f = new Flight();
                    f.setDepartureFrom(flight.getDepartureFrom());
                    f.setDestination(flight.getDestination());
                    f.setDepartureFrom(flight.getDepartureFrom());
                    f.setTakeoff(flight.getTakeoff());
                    InlineResponse200Price price = flight.getPrice();
                    f.setPrice(price.getCurrency(), price.getAmount().intValue());
                    voli.add(f);
                }
                
        		offers.getOffers().put(result.getCompanyname(), voli);

            }else{
                System.out.println("vecchio è stra vuota");
            }
            //offers.setFlights(voli);
            saveJSON(offers);
        } catch (airlinetest.ApiException e) {
            System.err.println("Exception when calling RisorseApi#getFlights");
            e.printStackTrace();
        }
    }

    public static void saveJSON(Offers offers){

        Gson j = new Gson();

        try {
            Writer writer;
            writer = new FileWriter(new File(StaticValues.offers_file_path));
            JsonWriter jsonWriter = new JsonWriter(writer);

            j.toJson(offers, Offers.class, jsonWriter);

            jsonWriter.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static Offers getJSON() {
    	
    	Offers o = null;
		File file = new File(StaticValues.offers_file_path);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
				
			Gson j = new Gson();
			FileReader fileReader = new FileReader(file);
			o = j.fromJson(fileReader, Offers.class);
			
			fileReader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return o; 
    	
    }

    //aggiungere funzione che apre il file e restituisce le offers dal json

}



