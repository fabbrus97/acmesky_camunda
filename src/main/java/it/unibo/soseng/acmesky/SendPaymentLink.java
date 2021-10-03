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

		
		sendPaymentLinkService.service(execution.getProcessEngine().getRuntimeService(), 
				 execution.getVariable("code2check").toString());
	}

}
