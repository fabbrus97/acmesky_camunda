package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;


public class SendCodes implements JavaDelegate{

	@Inject
	SendCodeService sendCodeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		if ((boolean)execution.getVariable("matchFound")) {
				System.out.println("Chiamo servizio 'sendCodeService'");
				sendCodeService.service(((ArrayList<String>)execution.getVariable("prontograms")).get(0));
		} 
		
	}
	
	

}
	