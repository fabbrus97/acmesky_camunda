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
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_code == null || t.username == null);
		
		
		//Immaginiamo il seguente scenario: acmesky trova 2 offerte per un nostro interesse.
		//Se noi completiamo il processo per l'acquisto della prima offerta, e rimuoviamo quindi 
		//la transazione associata dalla lista delle transazioni, perdiamo tutti i dati a noi associati
		//(nome utente, indirizzo) e quando acmesky ci manderà il nuovo codice offerta non potremmo associarlo
		//a nessuna transazione (in particolare, a nessun indirizzo). 
		
		//Per questo, dopo aver pulito le transazioni, controlliamo che ce ne sia almeno una con i nostri dati utente
		//e se non c'è la creiamo ad hoc.
		
		Transazione tmp = null;
		for (Transazione t: StaticValues.transazioni) {
			if (t.acmesky_code.contentEquals(acmesky_code)) {
				System.out.println("Ho inizializzato tmp");
				tmp = t; 
				tmp.flight = null;
				tmp.acmesky_code = "";
				tmp.payment_link = "";
				break;
			}
		}
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_code.contentEquals(acmesky_code) );
		System.out.println("CLIENTE: stato interno ripristinato, rimosso codice " + acmesky_code);
		System.out.println("CLIENTE: stato interno attuale: ");
		
		boolean found = false;
		for (Transazione t: StaticValues.transazioni) {
			if (t.username.contentEquals(tmp.username)) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			StaticValues.transazioni.add(tmp);
		}
		
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
