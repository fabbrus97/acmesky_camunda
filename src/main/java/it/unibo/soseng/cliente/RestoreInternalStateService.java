package it.unibo.soseng.cliente;

public class RestoreInternalStateService {
	
	public RestoreInternalStateService() {
		
	}
	
	public static void service(String username, String flight_code) {
		
		//La transazione può essere cancellata qua, oppure quando riceviamo la richesta per la navetta
		//Non è un problema se viene cancellata prima di ricevere la richiesta della navetta, perché
		//acmesky manda il codice offerta associato all'utente e noi dobbiamo rimandarglielo con la risposta
		// - se accettiamo o rifiutiamo il trasporto -, quindi non dobbiamo inviargli alcun dato nella Transazione
		
		System.out.println("CLIENTE: ripristino stato interno per il volo " + flight_code);
		
		StaticValues.transazioni.removeIf( t -> t.flight.getOfferCode().contentEquals(username) && t.flight.getOfferCode().contentEquals(username));
		
	}
	
}
