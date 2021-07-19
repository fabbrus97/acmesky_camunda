package it.unibo.soseng.acmesky;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.client.model.Body1;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.LinkAmount;
import io.swagger.client.model.MapsV1Credentials;
import io.swagger.paymentprovider.*;

import io.swagger.paymentprovider.auth.*;
import io.swagger.paymentprovider.payment_client.*;
public class GetLink implements JavaDelegate{

	@Inject
	GetLinkService getLinkService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		execution.setVariable("paymentLink", getLinkService.service());
		
	}

}
