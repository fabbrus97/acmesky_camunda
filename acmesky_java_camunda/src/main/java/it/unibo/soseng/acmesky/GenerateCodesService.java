package it.unibo.soseng.acmesky;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.*;

public class GenerateCodesService {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	
	public GenerateCodesService() {
		
	}
	
	public static void service(ArrayList<String[]> matches) {
		//le coppie sono formate da <id_cliente, codice_volo>
		
		if (matches.isEmpty()) {
			System.out.println("Non ho trovato match, non genero codici");
		}
		
		for (String[] match: matches) {
			System.out.println("Genero un nuovo codice per " + match[0] + " " + match[1]);
			save_new_code(match[0], match[1]);
		}
		
	}
	
	private static void save_new_code(String user, String fly_code) {

		Codes c  = deserialize_file();
		boolean habemus_codex;
		String new_random_code; 
		while(true) {
			habemus_codex = true;
			//genera 6 caratteri alfanumerici random
			new_random_code = "";
			for (int i = 0; i < 6; i++) {
				int digit = ((int)(Math.random()*100)%36);
				new_random_code += Character.forDigit(digit, Character.MAX_RADIX);
			}
			//controlla se esistono già
			for (Code code: c.getCodes()) {
				if (code.getCode() == new_random_code ) {
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
		new_code.setUser(user);
		new_code.setSent(false);
		  
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
