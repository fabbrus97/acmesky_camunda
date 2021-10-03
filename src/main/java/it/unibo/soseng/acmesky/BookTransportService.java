package it.unibo.soseng.acmesky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPBinding;

import it.unibo.soseng.acmesky.gen.Aeroporto;
import it.unibo.soseng.acmesky.gen.Indirizzo;
import it.unibo.soseng.acmesky.gen.NoleggioPort;
import it.unibo.soseng.acmesky.gen.NoleggioPortService;
import it.unibo.soseng.acmesky.gen.Richiesta.Data;
import it.unibo.soseng.acmesky.gen.Richiesta.Luoghi;
import it.unibo.soseng.acmesky.gen.Richiesta.Ora;

public class BookTransportService {
	
	private final static String RESPONSE_OK = "Nuovo utente creato";
	private final static String USER_REGISTERED = "Username esistente";
	private static DateTimeFormatter dtf_flights = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mma, 'CET'");
	//Nota: il formato dell'ora va da 1 a 23 - cioè ad esempio l'ora 23:15 è legale -, però viene comunque specificato dopo se è AM o PM anche se non necessario
	public BookTransportService() {
		
	}
	
	public static void service(String acmesky_offercode, String companyUrl) {
		
		Luoghi luoghi = new Luoghi();
		Data data = new Data();
		Ora ora = new Ora();
		for (Transazione t : StaticValues.transazioni) {
			if (t.acmesky_offer_code.contentEquals(acmesky_offercode)) {
				Indirizzo i = new Indirizzo();
				//semplificazione: non parsiamo completamente l'indirizzo che inserisce l'utente, supponiamo che ci sia solo la via e il comune dopo una virgola
				//(perché in Indirizzo potremmo inserire molti più campi, come il cap e il numero civico)
				i.setVia(t.home_address.split(",")[0]);
				i.setComune(t.home_address.split(",")[1]);
				
				Aeroporto aeroporto = new Aeroporto();
				aeroporto.setAeroporto(t.flight.getDepartureFrom());
				
				luoghi.setPartenza(i);
				luoghi.setArrivo(aeroporto); 
				
				System.out.println("ACMESKY: provo a formattare " + t.flight.getTakeoff());
				LocalDateTime ldt = LocalDateTime.from(dtf_flights.parse(t.flight.getTakeoff()));
				data.setGiorno(ldt.getDayOfMonth()); data.setMese(ldt.getMonthValue()); data.setAnno(ldt.getYear());
				ora.setHh(ldt.getHour()); ora.setMm(ldt.getMinute());
				
			}
		}
		
		System.out.println("Acmesky: cerco una navetta per il trasporto del cliente");
		
		NoleggioPortService myService = new NoleggioPortService();
		
		QName qname = new QName("http://localhost:8000", "NoleggioPortServicePort");
		myService.addPort(qname, SOAPBinding.SOAP11HTTP_BINDING, companyUrl+"/NoleggioPortService");
		
	    //NoleggioPort noleggioPort = myService.getNoleggioPortServicePort();
	    NoleggioPort noleggioPort = myService.getPort(qname, NoleggioPort.class);
	    
		String r;
		System.out.println("ACMESKY: richiesta per compagnia trasporti...");
	    if (StaticValues.token_compagnia_trasporti.isEmpty()) {
	    	System.out.println("ACMESKY: richiesta registrazione compagnia trasporti");
	    	r = noleggioPort.registrazione(StaticValues.prontogram_password, StaticValues.prontogram_username); //possiamo usare le stesse credenziali
	    	/*if (r.contentEquals(RESPONSE_OK)) {
	    		System.out.println("Acmesky: richiesta prenotazione dopo registrazione servizio trasporto navette");
	    		richiesta(noleggioPort, luoghi, data, ora);
		    } else if (r.contentEquals(USER_REGISTERED)) {
		    	richiesta(noleggioPort, luoghi, data, ora);
		    }*/
	    } //else {
    	
    	richiesta(noleggioPort, luoghi, data, ora);
	    //}
	    	    
	}
	
	private static void richiesta(NoleggioPort noleggioPort, Luoghi luoghi, Data data, Ora ora) {
		
		String sid = noleggioPort.login(StaticValues.prontogram_password, StaticValues.prontogram_username);
		if (!sid.isEmpty()) {
			System.out.println("ACMESKY: sid non è vuoto, faccio la richiesta per navetta");
			noleggioPort.richiesta(luoghi, data, ora, sid); 
			System.out.println("ACMESKY: richiesta effettuata");
		} else {
			System.out.println("ACMESKY: sid è vuoto, non faccio la richiesta");
		}
	}
}
