package it.unibo.soseng.acmesky;

import java.util.ArrayList;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FindMatch implements JavaDelegate{
	
	@Inject
	private FindMatchService findMatchService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//restituisce una lista di coppie formate da <id_cliente, codice_volo>
		ArrayList<String[]> matches = findMatchService.service();
		
		if (matches != null && matches.size() > 0) {
			execution.setVariable("matchFound", true);
			execution.setVariable("matches", matches);
		} else {
			execution.setVariable("matchFound", false);
			System.out.println("Non ho trovato alcun match");
		}
		
	}

}
