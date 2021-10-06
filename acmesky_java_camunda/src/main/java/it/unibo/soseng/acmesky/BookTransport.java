package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import it.unibo.soseng.acmesky.gen.NoleggioPort;
import it.unibo.soseng.acmesky.gen.NoleggioPortService;
import it.unibo.soseng.acmesky.gen.Richiesta.Data;
import it.unibo.soseng.acmesky.gen.Richiesta.Luoghi;
import it.unibo.soseng.acmesky.gen.Richiesta.Ora;
import it.unibo.soseng.acmesky.gen.RegistrazioneResponse;

public class BookTransport implements JavaDelegate {

	@Inject
	BookTransportService bookTransporService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//la variabile acmesky_code viene impostata dal messaggio dell'utente quando ci contatta
		
		System.out.println("L'utente ha accettato la navetta per il codice offerta " + execution.getVariable("acmesky_code"));
		String acmesky_code = execution.getVariable("acmesky_code").toString();
		String companyUrl = execution.getVariable("transportCompanyUrl").toString();
	    BookTransportService.service(acmesky_code, companyUrl);
	    
		
	}

	
}
