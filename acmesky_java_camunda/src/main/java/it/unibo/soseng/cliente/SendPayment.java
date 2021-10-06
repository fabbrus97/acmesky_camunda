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
		
		String paymentLink = execution.getVariable("paymentLink").toString();
		String code = execution.getVariable("code").toString();
		
		System.out.println("CLIENTE: ho ricevuto un codice da acmesky " + code);
		
		sendPaymentService.service(paymentLink, StaticValues.paymentUrl, code); 
		
	}

}