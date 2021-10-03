package it.unibo.soseng.cliente;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ManageTransportOffer implements JavaDelegate{

	@Inject
	ManageTransportOfferService manageTransportOfferService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//per sapere quale utente/offerta sta ricevendo l'offerta, acmesky ci manda anche il suo codice offerta come identificatore
		//quindi la variabile "acmesky_code" è settata da acmesky quando ci manda il messaggio
		manageTransportOfferService.service(execution.getProcessEngine().getRuntimeService(), execution.getVariable("acmesky_code").toString());
		
		
	}

}
