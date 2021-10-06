package it.unibo.soseng.acmesky;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;


public class InitListener implements ExecutionListener {
	
	private enum services {PAYMENT, PRONTOGRAM, AIRLINE, GEOPROVIDER};
	
	@Override
    public void notify(DelegateExecution execution) throws Exception {
     

        /*nomi e percorsi dei file:
	     server_list/airline.list  
	     server_list/geo-distance-provider.list  
	     server_list/payment-provider.list  
	     server_list/prontogram.list
	    */
		
		mySetVariable(execution, "server_list/airline.list", "airlines");
		mySetVariable(execution, "server_list/geo-distance-provider.list", "geoproviders");
		mySetVariable(execution, "server_list/payment-provider.list", "payments");
		mySetVariable(execution, "server_list/prontogram.list", "prontograms");
		mySetVariable(execution, "server_list/transport.list", "transports");
		
	}
	
	void mySetVariable(DelegateExecution execution, String fileName, String varName) throws Exception {
		System.out.println("ACMESKY Eseguo init listener");
		StaticValues.airlinesUrl.clear();
		StaticValues.transports.clear(); 
				
		ArrayList<String> urlList = new ArrayList<String>();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
		
		String url = bufferedReader.readLine();
		while(url != null) {
			
			if (varName.equals("airlines")) {
				StaticValues.airlinesUrl.add(url);
			}
			
			if (varName.equals("payments")) {
				StaticValues.paymentUrl = url;
			}
			
			if (varName.equals("geoproviders")) {
				StaticValues.geoproviderUrl = url;
			}
			
			if (varName.equals("prontograms")) {
				StaticValues.prontogramUrl = url;
			}
			
			if (varName.contentEquals("transports")){
				//l'elenco dei server dei trasporti differisce in quanto è composto da due parti separate da uno spazio: url + indirizzo
				String actual_url = url.split(" ")[0];
				String address = url.replace(actual_url, "").substring(1);
				StaticValues.transports.add(new String[] {actual_url, address});
			}
			
			urlList.add(url);

			url = bufferedReader.readLine();
			
		}
		
		if (varName.contentEquals("airlines")) {
			execution.setVariable("airlines", urlList);
		}
        
	}
		
}