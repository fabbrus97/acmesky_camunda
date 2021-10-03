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
	     /public/simonef/server_list/airline.list  
	     /public/simonef/server_list/geo-distance-provider.list  
	     /public/simonef/server_list/payment-provider.list  
	     /public/simonef/server_list/prontogram.list
	    */
				
		setVariable(execution, "server_list/airline.list", "airlines");
		setVariable(execution, "server_list/geo-distance-provider.list", "geoproviders");
		setVariable(execution, "server_list/payment-provider.list", "payments");
		setVariable(execution, "server_list/prontogram.list", "prontograms");
		setVariable(execution, "server_list/transport.list", "transports");
		
	}
	
	void setVariable(DelegateExecution execution, String fileName, String varName) throws Exception {
		
		StaticValues.airlinesUrl.clear();
		StaticValues.transports.clear(); 
		
		System.out.println("Leggo la lista dei server...");
		
		ArrayList<String> urlList = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
		
		String url = bufferedReader.readLine();
		while(url != null) {
			
			if (varName.equals("airlines")) {
				StaticValues.airlinesUrl.add(url); //TODO forse dev'essere una hashmap e non una lista, nel caso dobbiamo distinguere le compagnie aeree - tipo per mandare notifiche?
			}
			
			if (varName.equals("payments")) {
				System.out.println("HO IMPOSTATO UN URL PER PAYMENTS: " + url);
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
				System.out.println("Ho aggiunto server dei trasporti con indirizzo " + address);
			}
			
			urlList.add(url);
			System.out.println("Eseguo init listener per " + varName + ": " + url);
			url = bufferedReader.readLine();
			
		}
        
		
        execution.setVariable(varName, urlList);
	}
		
}