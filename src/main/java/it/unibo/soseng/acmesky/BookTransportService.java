package it.unibo.soseng.acmesky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPBinding;
import it.unibo.soseng.acmesky.gen.Indirizzo;
import it.unibo.soseng.acmesky.gen.NoleggioPort;
import it.unibo.soseng.acmesky.gen.NoleggioPortService;
import it.unibo.soseng.acmesky.gen.Richiesta.Data;
import it.unibo.soseng.acmesky.gen.Richiesta.Luoghi;
import it.unibo.soseng.acmesky.gen.Richiesta.Ora;

public class BookTransportService {
	
	private final static String RESPONSE_OK = "Nuovo utente creato";
	private final static String USER_REGISTERED = "Username esistente";
	private static DateTimeFormatter dtf_flights = DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mma, 'CET'");
	
	public BookTransportService() {
		
	}
	
	public static void service(String acmesky_offercode) {
		
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
				luoghi.setPartenza(i);
				
				LocalDateTime ldt = LocalDateTime.from(dtf_flights.parse(t.flight.getTakeoff()));
				data.setGiorno(ldt.getDayOfMonth()); data.setMese(ldt.getMonthValue()); data.setAnno(ldt.getYear());
				ora.setHh(ldt.getHour()); ora.setMm(ldt.getMinute());
				
			}
		}
		
		System.out.println("Acmesky: cerco una navetta per il trasporto del cliente");
		
		NoleggioPortService myService = new NoleggioPortService();
		
		QName qname = new QName("http://localhost:8000", "NoleggioPortServicePort");
		myService.addPort(qname, SOAPBinding.SOAP11HTTP_BINDING, "http://localhost:8000/NoleggioPortService");
		
	    //NoleggioPort noleggioPort = myService.getNoleggioPortServicePort();
	    NoleggioPort noleggioPort = myService.getPort(qname, NoleggioPort.class);
	    
		String r;
	    if (StaticValues.token_compagnia_trasporti.isEmpty()) {
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
		if (!sid.isEmpty())
			noleggioPort.richiesta(luoghi, data, ora, sid);
	}
}
