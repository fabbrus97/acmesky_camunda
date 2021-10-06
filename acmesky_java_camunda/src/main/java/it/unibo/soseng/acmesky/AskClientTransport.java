package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AskClientTransport implements JavaDelegate{

	@Inject
	AskClientTransportService askClientTransportService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		askClientTransportService.service(execution);

	}

}
