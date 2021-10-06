package it.unibo.soseng.cliente;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;



public class ThinkAboutOffer implements JavaDelegate{

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//c'è una probabilità del 20% di rifiutare l'offerta
		boolean refuse = (int)(Math.random() * 10) >= 8;  
		System.out.println("Refuse: " + refuse);	
		if (refuse) {
			System.out.println("Rifiutiamo l'offerta");
		} else {
			System.out.println("Accettiamo l'offerta");
		}
		execution.setVariable("ignoreOffer", refuse);
	}

}
