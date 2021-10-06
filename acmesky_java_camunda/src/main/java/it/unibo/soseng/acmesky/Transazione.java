package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import it.unibo.soseng.acmesky.Json.Flight;

public class Transazione {
	//Questa classe contiene i dati di una transazione.
	//Con "transazione" si intende l'insieme dei passaggi che vanno da quando l'utente invia il proprio codice, all'eventuale prenotazione della navetta.
	//I dati sono il nome utente, il link del pagamento, il costo del volo, indirizzo dell'utente, aerporto di partenza e arrivo...
	
	//La lista delle transazioni attive è presente in StaticValues.
	
	//Il motivo è che tutti questi dati devono essere acceduti nei vari passaggi di una transazione (generazione del link di pagamento, prenotazione della navetta,...)
	// e quindi è comodo averli a disposizione facilmente e velocemente. 
	
	String username; 
	String home_address;
	
	//nota: un utente potrebbe avere più transazioni attive nello stesso momento. Assumiamo però che abbia un solo indirizzo.
	//ArrayList<DatiTransazione> datiTransizione;
	
	String paymentLink;
	String acmesky_offer_code;
	Flight flight;
	String company_name;
	
	public Transazione(String username) {
		this.username = username;
	}
	
}
