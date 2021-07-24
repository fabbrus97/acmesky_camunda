package it.unibo.soseng.acmesky;

import javax.inject.Inject;	

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.airline.*;
import io.swagger.airline.auth.*;
import io.swagger.client.model.*;
import io.swagger.airline.airline_client.RisorseApi;

public class GetOffers implements JavaDelegate {


    @Inject
    private GetOffersService getOffersService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String airline = execution.getVariable("airline").toString();
        getOffersService.service(airline);

    }
}
