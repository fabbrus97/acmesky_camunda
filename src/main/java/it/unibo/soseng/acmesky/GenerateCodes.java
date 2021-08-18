package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GenerateCodes implements JavaDelegate{

	@Inject
	GenerateCodesService generateCodesService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		generateCodesService.service((ArrayList<String[]>)execution.getVariable("matches"));
		
	}

}
