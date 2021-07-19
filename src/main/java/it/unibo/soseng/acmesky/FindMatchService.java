package it.unibo.soseng.acmesky;

import java.util.ArrayList;
import it.unibo.soseng.acmesky.Json.*;

public class FindMatchService {
	
	
	public FindMatchService() {
		
	}
	
	public static ArrayList<String[]> service() {
		//restituiamo una lista di coppie di stringhe
		//le coppie sono formate da <id_cliente, codice_volo>
		
		ArrayList<String[]> matches = new ArrayList<String[]>();
		
		//apri la lista degli interessi
		Clients c =  SaveInterestService.deserialize_file();
		
		//apri la lista dei voli
		//TODO
		
		//per ogni interesse, cerca se esiste un volo compatibile
		c.getClients().forEach((client, interests) -> {
			for (Interest interest : interests.getInterests()) {
				//foreach flight ; if flight == interest //TODO
				String[] match = {client, "codice volo"};  //TODO
				matches.add(match);
			}
		});
			
		
		//se esiste, aggiungilo alla lista
		
		return matches.isEmpty() ? null : matches ;
		
	}
	
	
	
}
