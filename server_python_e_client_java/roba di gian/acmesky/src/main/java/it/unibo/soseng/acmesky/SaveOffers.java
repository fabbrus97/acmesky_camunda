package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import io.swagger.client.model.InlineResponse2001;

import java.util.ArrayList;
import java.util.Map;

public class SaveOffers implements JavaDelegate {
	
	@Inject
	private SaveOfferService saveOfferService; 
	
	@Override
    public void execute(DelegateExecution execution) throws Exception {
		
		saveOfferService.service((InlineResponse2001)execution.getVariable("offers"));

		//richiedi le offerte
		//dopo l'ottenimento aggiungi le offerte ad un file
		//cancella le vecchie offerte

		
	}

}

