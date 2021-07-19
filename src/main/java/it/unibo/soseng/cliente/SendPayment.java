package it.unibo.soseng.cliente;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendPayment implements JavaDelegate{

	@Inject
	SendPaymentService sendPaymentService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		sendPaymentService.service("link"); //TODO
		
	}

}