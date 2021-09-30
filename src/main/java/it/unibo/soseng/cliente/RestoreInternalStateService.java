package it.unibo.soseng.cliente;

public class RestoreInternalStateService {
	
	public RestoreInternalStateService() {
		
	}
	
	public static void service(String trans_id, String username, String acmesky_code) {
		
		//La transazione può essere cancellata qua, oppure quando riceviamo la richesta per la navetta
		//Non è un problema se viene cancellata prima di ricevere la richiesta della navetta, perché
		//acmesky manda il codice offerta associato all'utente e noi dobbiamo rimandarglielo con la risposta
		// - se accettiamo o rifiutiamo il trasporto -, quindi non dobbiamo inviargli alcun dato nella Transazione
		
		System.out.println("CLIENTE: ripristino stato interno l'offerta" + acmesky_code + " e trans_id " + trans_id);
		
		for (Transazione t: StaticValues.transazioni) {
			if (t.username != null)
				 System.out.println(t.username);
			else {
				 System.out.println("username è null");
			}
			
			if (t.acmesky_code!= null)
				 System.out.println(t.acmesky_code);
			else {
				 System.out.println("acmesky_code è null");
			}
			
			if (t.home_address != null)
				 System.out.println(t.home_address );
			else {
				 System.out.println("home address è null");
			}
			
			if (t.payment_link  != null)
				 System.out.println(t.payment_link );
			else {
				 System.out.println("payment link è null");
			}
			
			if (t.flight != null)
				 System.out.println(t.flight.getDepartureFrom() + "-" + t.flight.getDestination() + " " + t.flight.getOfferCode());
			else {
				 System.out.println("flight è null");
			}
			
			System.out.println("-----------------------------------------");
			
		}
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_code == null);
		StaticValues.transazioni.removeIf( t -> t.acmesky_code.contentEquals(acmesky_code) );
		System.out.println("CLIENTE: stato interno ripristinato, rimosso codice " + acmesky_code);
		System.out.println("CLIENTE: stato interno attuale: ");
		
		for (Transazione t: StaticValues.transazioni) {
			if (t.username != null)
				 System.out.println(t.username);
			else {
				 System.out.println("username è null");
			}
			
			if (t.acmesky_code!= null)
				 System.out.println(t.acmesky_code);
			else {
				 System.out.println("acmesky_code è null");
			}
			
			if (t.home_address != null)
				 System.out.println(t.home_address );
			else {
				 System.out.println("home address è null");
			}
			
			if (t.payment_link  != null)
				 System.out.println(t.payment_link );
			else {
				 System.out.println("payment link è null");
			}
			
			if (t.flight != null)
				 System.out.println(t.flight.getDepartureFrom() + "-" + t.flight.getDestination() + " " + t.flight.getOfferCode());
			else {
				 System.out.println("flight è null");
			}
			
			System.out.println("-----------------------------------------");
			
		}
		
		//StaticValues.transazioni.removeIf( t -> t.flight.getOfferCode().contentEquals(username) && t.username.contentEquals(username));
		
	}
	
}
