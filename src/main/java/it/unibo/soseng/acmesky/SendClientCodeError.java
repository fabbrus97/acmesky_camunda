package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendClientCodeError implements JavaDelegate {

	@Inject
	SendClientCodeErrorService sendClientCodeErrorService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		sendClientCodeErrorService.service(execution);
	}
	
}
