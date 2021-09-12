package it.unibo.soseng.cliente;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import it.unibo.soseng.acmesky.StaticValues;

public class SendPayment implements JavaDelegate{

	@Inject
	SendPaymentService sendPaymentService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String paymentLink = execution.getVariable("paymentLink").toString(); //TODO Null pointer exception
		String username = execution.getVariable("username").toString();
		
		sendPaymentService.service(paymentLink, StaticValues.paymentUrl, username); 
		
	}

}