package it.unibo.soseng.acmesky;

import it.unibo.soseng.acmesky.gen.NoleggioPort;
import it.unibo.soseng.acmesky.gen.NoleggioPortService;
import it.unibo.soseng.acmesky.gen.Richiesta.Data;
import it.unibo.soseng.acmesky.gen.Richiesta.Luoghi;
import it.unibo.soseng.acmesky.gen.Richiesta.Ora;

public class BookTransportService {
	
	private final static String RESPONSE_OK = "Nuovo utente creato";
	
	public BookTransportService() {
		
	}
	
	public static void service(Luoghi luoghi, Data data, Ora ora) {
		NoleggioPortService myService = new NoleggioPortService();
		
	    NoleggioPort noleggioPort = myService.getNoleggioPortServicePort();
	    
		String r;
	    if (StaticValues.token_compagnia_trasporti.isEmpty()) {
	    	r = noleggioPort.registrazione(StaticValues.prontogram_username, StaticValues.prontogram_password); //possiamo usare le stesse credenziali
	    	if (r.contentEquals(RESPONSE_OK)) {
	    		richiesta(noleggioPort, luoghi, data, ora);
		    }
	    } else {
	    	richiesta(noleggioPort, luoghi, data, ora);
	    }
	    
	}
	
	private static void richiesta(NoleggioPort noleggioPort, Luoghi luoghi, Data data, Ora ora) {
		
		String sid = noleggioPort.login(StaticValues.prontogram_password, StaticValues.prontogram_username);
		if (!sid.isEmpty())
			noleggioPort.richiesta(luoghi, data, ora, sid);
	}
}
