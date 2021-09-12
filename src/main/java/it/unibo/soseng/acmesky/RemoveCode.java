package it.unibo.soseng.acmesky;

import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RemoveCode implements JavaDelegate{

	@Inject
	RemoveCodeService removeCodeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
			
		removeCodeService.service(execution);
		
		
		/*String link = StaticValues.transazioni.getFirst().paymentLink; // execution.getVariable("paymLink");
		if (link != null) {
			String code = StaticValues.transazioni.getFirst().acmesky_offer_code; //StaticValues.codes2delete.get(link);
			if (code != null) {
				removeCodeService.service(execution, code);
			} 
		}*/
	}

}