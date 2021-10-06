package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;

import javax.inject.Inject;

public class GetDistanceAirline implements JavaDelegate{

	@Inject
	private GetDistanceService getDistanceService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		getDistanceService.service(execution, false);
		
	}

}
