package it.unibo.soseng.acmesky;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;

public class GetDistanceTransport implements JavaDelegate{

	@Inject
	private GetDistanceService getDistanceService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		getDistanceService.service(execution, true);
		
	}

}
