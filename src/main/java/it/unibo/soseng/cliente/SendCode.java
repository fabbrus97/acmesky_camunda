package it.unibo.soseng.cliente;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCode implements JavaDelegate{

	@Inject
	SendCodeService sendCodeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		sendCodeService.service(execution.getProcessEngine().getRuntimeService(), execution.getVariable("code").toString());
		
	}

}