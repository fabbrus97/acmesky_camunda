package it.unibo.soseng.acmesky;

import javax.inject.Inject;	

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class GetOffers implements JavaDelegate {


    @Inject
    private GetOffersService getOffersService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    	System.out.println("Devo chiamare il service...");
        String airline = execution.getVariable("airline").toString();
        getOffersService.service(airline);

    }
}
