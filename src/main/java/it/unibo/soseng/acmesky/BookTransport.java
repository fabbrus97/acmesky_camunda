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
		// TODO Auto-generated method stub
		
		
	    BookTransportService.service((Luoghi) execution.getVariable("luoghi"), (Data) execution.getVariable("data"), (Ora) execution.getVariable("ora"));
	    
	    
	    
	    //String greet = ws.hello((String)execution.getVariable("customerId"));
	    //execution.setVariable("greet", greet);
		
	}

	
}
