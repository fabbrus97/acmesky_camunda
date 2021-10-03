package it.unibo.soseng.acmesky;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.LinkAmount;
import io.swagger.client.model.MapsV1Credentials;

public class GetLink implements JavaDelegate{

	@Inject
	GetLinkService getLinkService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		getLinkService.service(execution.getVariable("code2check").toString());
		
	}

}
