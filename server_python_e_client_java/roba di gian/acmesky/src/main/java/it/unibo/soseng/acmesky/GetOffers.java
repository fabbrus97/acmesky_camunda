package it.unibo.soseng.acmesky;

import javax.inject.Inject;	

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.airline.*;
import io.swagger.airline.auth.*;
import io.swagger.client.model.*;
import io.swagger.airline.airline_client.RisorseApi;

public class GetOffers implements JavaDelegate {

	@Override
    public void execute(DelegateExecution execution) throws Exception {
        InlineResponse2001 result = GetOffersService.service((String) execution.getVariable("airline"));
        execution.setVariable("offers", result);
    }
}
