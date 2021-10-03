package it.unibo.soseng.acmesky;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.*;


public class SaveInterestService {

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public SaveInterestService(){

	}

	public static void service(String departure_airport, String arrival_airport, String departure_time_min, String departure_time_max,
			String arrival_time_min, String arrival_time_max, String client_id, String clientAddress, int cost) {

		//salviamo gli interessi su un file json; dobbiamo quindi prima aprirlo, parsarlo, aggiungere l'interesse
		//un cliente potrebbe avere più di un interesse), e risalvare il tutto

		/* composizione del file:
		 * "clients": {
		 * 		"id_client": [{"cost", ...}],
		 * }
		*/

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
				System.out.println("ACMESKY: sto aggiungengo l'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport());
				c.setInterests(interests);
				c.setClientAddress(clientAddress);
				clients.clients.put(client_id, c);
			}

			//riscriviamo i risultati
			serialize_json(clients);
		} 

	}

	protected static Clients deserialize_file() {

		Clients c = null;
		File file = new File(StaticValues.client_interests_file_path);

		try {
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write("{ \"clients\": {} }".getBytes());
				fos.close();

			}

			Gson j = new Gson();
			FileReader fileReader = new FileReader(file);



			c = j.fromJson(fileReader, Clients.class);

			fileReader.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return c;
	}

	public static void serialize_json(Clients clients) {
		Gson j = new Gson();

		//prima di scrivere il json, rimuoviamo i voli scaduti
		clients = removeOldFlights(clients);

		try {
			Writer writer;
			writer = new FileWriter(new File(StaticValues.client_interests_file_path));
			JsonWriter jsonWriter = new JsonWriter(writer);

			j.toJson(clients, Clients.class, jsonWriter);

			jsonWriter.flush();

			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Clients removeOldFlights(Clients clients) {
		clients.getClients().forEach( (username, client) -> {
			ArrayList<Interest> elements2delete = new ArrayList<Interest>();
			client.interests.forEach(interest -> {
				LocalDateTime ld = LocalDateTime.from(dtf.parse(interest.getDeparture_time_max()));

				if (ld.isBefore(LocalDateTime.now())){
					System.out.println("Trovato volo scaduto per cliente " + username);
					elements2delete.add(interest);
				}
			});
			client.getInterests().removeAll(elements2delete);
		});
		return clients;
	}



}
