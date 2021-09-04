package it.unibo.soseng.cliente;

import it.unibo.soseng.acmesky.Json.Flight;

public class Transazione {
	
	String username;
	String home_address; //l'indirizzo dev'essere formattato come via, comune
	String acmesky_code;
	Flight flight; 
	String payment_link;
	
	public Transazione(String username) {
		this.username = username;
	}
	
}
