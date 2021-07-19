package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;


public class SendCodes implements JavaDelegate{

	@Inject
	SendCodeService sendCodeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		sendCodeService.service();				
		
	}
	
	

}
