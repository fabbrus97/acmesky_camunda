package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendPaymentLink implements JavaDelegate {

	@Inject
	SendPaymentLinkService sendPaymentLinkService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		//TODO questa classe deve anche mandare la notifica al cliente di codice offerta acmesky errato
		
		sendPaymentLinkService.service(execution.getProcessEngine().getRuntimeService(), 
				 execution.getVariable("code2check").toString());
	}

}
