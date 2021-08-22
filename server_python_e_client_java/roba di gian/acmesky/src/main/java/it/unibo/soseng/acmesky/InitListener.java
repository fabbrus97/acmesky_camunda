package it.unibo.soseng.acmesky;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;


public class InitListener implements ExecutionListener {
	
	@Override
    public void notify(DelegateExecution execution) throws Exception {

		setVariable(execution,"/public/simonef/server_list/airline.list",airlines);

		ArrayList<String> airlines = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/airline.list")));

		String airline = bufferedReader.readLine();
		while(airline != null) {
			airlines.add(airline);
			airline = bufferedReader.readLine();
		}

		execution.setVariable("airlines", airlines);
//-----------------------------------------------------------------
		ArrayList<String> geoProvider = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/geo-distance-provider.list")));

		String gprov = bufferedReader.readLine();
		while(gprov != null) {
			geoProvider.add(gprov);
			gprov = bufferedReader.readLine();
		}

		execution.setVariable("geoProvider", geoProvider);
//-----------------------------------------------------------------
		ArrayList<String> payProvider = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/payment-provider.list")));

		String pprov = bufferedReader.readLine();
		while(pprov != null) {
			payProvider.add(pprov);
			pprov = bufferedReader.readLine();
		}

		execution.setVariable("payProvider", payProvider);
//-----------------------------------------------------------------
		ArrayList<String> prontogram = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/prontogram.list")));

		String pronto = bufferedReader.readLine();
		while(pronto != null) {
			prontogram.add(pronto);
			pronto = bufferedReader.readLine();
		}

		execution.setVariable("payProvider", prontogram);

	}
	
	void setVariable(DelegateExecution execution, String fileName, String varName) throws Exception {

		ArrayList<String> airlines = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/airline.list")));

		String airline = bufferedReader.readLine();
		while(airline != null) {
			airlines.add(airline);
			airline = bufferedReader.readLine();
		}

		execution.setVariable("airlines", airlines);
//-----------------------------------------------------------------
		ArrayList<String> geoProvider = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/geo-distance-provider.list")));

		String gprov = bufferedReader.readLine();
		while(gprov != null) {
			geoProvider.add(gprov);
			gprov = bufferedReader.readLine();
		}

		execution.setVariable("geoProvider", geoProvider);
//-----------------------------------------------------------------
		ArrayList<String> payProvider = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/payment-provider.list")));

		String pprov = bufferedReader.readLine();
		while(pprov != null) {
			payProvider.add(pprov);
			pprov = bufferedReader.readLine();
		}

		execution.setVariable("payProvider", payProvider);
//-----------------------------------------------------------------
		ArrayList<String> prontogram = new ArrayList<String>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/public/simonef/server_list/prontogram.list")));

		String pronto = bufferedReader.readLine();
		while(pronto != null) {
			prontogram.add(pronto);
			pronto = bufferedReader.readLine();
		}

		execution.setVariable("payProvider", prontogram);
	}
		
}