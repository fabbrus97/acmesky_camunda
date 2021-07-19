package it.unibo.soseng.acmesky;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.Client;
import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

public class GenerateCodesService {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public GenerateCodesService() {
		
	}

	public static void service() {
		
		/*TODO: apri lista voli
		 * apri lista interessi
		 * per ogni volo, se c'è un utente interessato aggiungilo alla lista "users"
		 * prima di passare al volo successivo, passa alla lista "fly_code"
		*/
		
		ArrayList<String> voli = new ArrayList<String>(); //TODO
		Clients clients = SaveInterestService.deserialize_file();
		for (String volo: voli) {
			ArrayList<String> interested_users = new ArrayList<String>();
			clients.getClients().forEach( (name, client) -> {
				client.getInterests().forEach(interest -> {
					LocalDateTime arrival_min = LocalDateTime.parse(interest.getArrival_time_min(), dtf);
					LocalDateTime arrival_max = LocalDateTime.parse(interest.getArrival_time_max(), dtf);
					
					boolean valid = within(arrival_min, arrival_max, null); //TODO
					
				});
			});
		}
		
		
		save_new_code(users, fly_code); //TODO
		
	}
	
	private static boolean within(LocalDateTime start, LocalDateTime end, LocalDateTime test) {
		return (start.isBefore(test) && end.isAfter(test)); 
	}

	private static void save_new_code(ArrayList<String> users, String fly_code) {

		Codes c  = deserialize_file();
		boolean habemus_codex;
		String new_random_code; 
		while(true) {
			habemus_codex = true;
			//genera 6 caratteri alfanumerici random
			new_random_code = "";
			//controlla se esistono già
			for (Code code: c.getCodes()) {
				if (code.getCode() == new_random_code) {
					LocalDateTime creation_date = LocalDateTime.parse(code.getCreation_date(), dtf);
					LocalDateTime now = LocalDateTime.now();
					Duration duration = Duration.between(creation_date.toLocalDate(), now.toLocalDate());
					if (duration.toHours() < 24) {
						habemus_codex = false;
						break;
					}
					else  { //abbiamo trovato un codice scaduto, lo possiamo "cancellare" e "riaggiungere"
						//habemus_codex = true è già vero
						c.getCodes().remove(code); 
					}
				}
			}
			
			if (habemus_codex)
				break; 
			
			
		}
		//salva la lista
		Code new_code = new Code();
		new_code.setCode(new_random_code);
		new_code.setFly_code(fly_code);
		new_code.setUsers(users);
		  
		LocalDateTime now = LocalDateTime.now();  
  		new_code.setCreation_date(dtf.format(now));
		c.getCodes().add(new_code);
		serialize_json(c);
	}
	
	protected static Codes deserialize_file() {
		Codes c = null;
		File file = new File(StaticValues.codes_file_path);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream fs = new FileOutputStream(file);
				fs.write("{ \"codes\": [] }".getBytes());
				fs.flush(); fs.close();

			}
				
			Gson j = new Gson();
			FileReader fileReader = new FileReader(file);
			c = j.fromJson(fileReader, Codes.class);
			
			fileReader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return c; 
	}
	
	protected static void serialize_json(Codes codes) {
		try {
			Gson j = new Gson();
			Writer writer;
			writer = new FileWriter(new File(StaticValues.codes_file_path));
			JsonWriter jsonWriter = new JsonWriter(writer);
			
			j.toJson(codes, Codes.class, jsonWriter);
			
			jsonWriter.flush();
			System.out.println("Fatto");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
