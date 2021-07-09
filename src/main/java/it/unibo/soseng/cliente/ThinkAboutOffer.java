package it.unibo.soseng.cliente;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;



public class ThinkAboutOffer implements JavaDelegate{

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("ignoreOffer", true);
	}

}
