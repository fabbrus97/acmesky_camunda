package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.client.model.Lmflight;

public class SaveOffersLM implements JavaDelegate{

	@Inject
	SaveOffersLMService saveOffersLMService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		saveOffersLMService.service(execution.getVariable("partenza").toString(), execution.getVariable("destinazione"), execution.getVariable("data").toString(), exec);
		
	}

}

