package it.unibo.soseng.acmesky;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

public class GenerateCodesService {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public GenerateCodesService() {
		
	}

	public static void service() {
		
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
					else  { //abbiamo trovato un codice scaduto, lo possiamo "cancellare" e "riusare"
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
		  
		LocalDateTime now = LocalDateTime.now();  
  		new_code.setCreation_date(dtf.format(now));
		c.getCodes().add(new_code);
	}

	protected static Codes deserialize_file() {
		Codes c = null;
		File file = new File(Static_Values.codes_file_path);
		
		try {
			if (!file.exists()) {
				file.createNewFile(); //TODO quando crea il file, 
				//deve essere fatto così:
				//{ "codes": [] } e non vuoto
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
	
}
