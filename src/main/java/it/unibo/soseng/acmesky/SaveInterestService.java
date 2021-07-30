package it.unibo.soseng.acmesky;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.*;


public class SaveInterestService {

	public SaveInterestService(){
		
	}
	
	public static void service(String departure_airport, String arrival_airport, String departure_time_min, String departure_time_max,
			String arrival_time_min, String arrival_time_max, String client_id, int cost) {
		
		//salviamo gli interessi su un file json; dobbiamo quindi prima aprirlo, parsarlo, aggiungere l'interesse 
		//un cliente potrebbe avere più di un interesse), e risalvare il tutto
		
		/* composizione del file: 
		 * "clients": {
		 * 		"id_client": [{"cost", ...}],
		 * }
		*/
		
		//implementazione alternativa: hashtable
		
		Clients clients = deserialize_file(); 
		if (clients != null) {
			
			Interest interest = new Interest();
			interest.setArrival_airport(arrival_airport);
			interest.setDeparture_airport(departure_airport);
			
			interest.setReturnHome_time_min(arrival_time_min);
			interest.setReturnHome_time_max(arrival_time_max);
			interest.setDeparture_time_min(departure_time_min);
			interest.setDeparture_time_max(departure_time_max);
			interest.setCost(cost);
		
			//cerchiamo se esiste già una entry per il client_id
			if (clients.clients.get(client_id) != null){
				
				clients.clients.get(client_id).interests.add(interest);
			} else { //crea nuovo client
				Client c = new Client();
				ArrayList<Interest> interests = new ArrayList<Interest>();
				interests.add(interest);
				c.setInterests(interests);
				clients.clients.put(client_id, c);
			}
			
			//riscriviamo i risultati
			serialize_json(clients);
		} else {
			System.out.println("clients è null, non posso fare niente");
		}
		
	}
	
	protected static Clients deserialize_file() {
		
		Clients c = null;
		File file = new File(StaticValues.client_interests_file_path);
		
		try {
			if (!file.exists()) {
				file.createNewFile(); //TODO quando crea il file, 
				//deve essere fatto così:
				//{ "clients": {} } e non vuoto
			}
				
			Gson j = new Gson();
			FileReader fileReader = new FileReader(file);
			c = j.fromJson(fileReader, Clients.class);
			
			fileReader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return c; 
	}
	
	private static void serialize_json(Clients clients) {
		System.out.println("Scriviamo il json finale...");
		Gson j = new Gson();
		
		try {
			Writer writer;
			writer = new FileWriter(new File(StaticValues.client_interests_file_path));
			JsonWriter jsonWriter = new JsonWriter(writer);
			
			j.toJson(clients, Clients.class, jsonWriter);
			
			jsonWriter.flush();
			System.out.println("Fatto");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	
}
