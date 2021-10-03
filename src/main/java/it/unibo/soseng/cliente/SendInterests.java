package it.unibo.soseng.cliente;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendInterests implements JavaDelegate{

	static SendInterestService sendInterestService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		sendInterestService.service(execution);
		
		
	}

}