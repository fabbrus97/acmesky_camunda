package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;


public class SendCodes implements JavaDelegate{

	@Inject
	SendCodesService sendCodeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		if ((boolean)execution.getVariable("matchFound")) {
				sendCodeService.service(StaticValues.prontogramUrl);
		} 
		
	}
	
	

}
	