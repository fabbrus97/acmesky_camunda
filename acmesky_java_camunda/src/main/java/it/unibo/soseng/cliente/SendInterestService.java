package it.unibo.soseng.cliente;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class SendInterestService {

    private static SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public SendInterestService() {
		
	}
	
	private static String[] airports = {"BLQ",
            "BGY",
            "CTA",
            "MXP",
            "VRN",
            "FCO",
            "LGW",
            "FRA",
            "BCN",
            "LIS",
            "AUH",
            "SVO", 
            "ORY"};

	public static void service(DelegateExecution execution) {
		
		Transazione t = new Transazione("");
		
		
		Cliente c = new Cliente("");
		c.payment_password = "1234567890";
		String username = "";
				
		if (execution.getVariable("customInterest") != null) {
			try {
				
				username = execution.getVariable("username").toString();
				t.username = username;
				c.payment_username = username;
				
				StaticValues.clienti.put(username, c);
				StaticValues.transazioni.add(t);
				
			    
				if (execution.getVariable("secondDate") != null && !execution.getVariable("secondDate").toString().isEmpty()) {
					execution.getProcessEngine().getRuntimeService().createMessageCorrelation("GetInterests")
						.setVariable("departure_airport", execution.getVariable("first_airport"))
						.setVariable("arrival_airport", execution.getVariable("second_airport"))	
						.setVariable("departure_time_min", sdt.format(sdt.parse(execution.getVariable("firstDate").toString())))
						.setVariable("departure_time_max", sdt.format(sdt.parse(execution.getVariable("firstDateInterval").toString())))
						.setVariable("arrival_time_min", sdt.format(sdt.parse(execution.getVariable("secondDate").toString())))
						.setVariable("arrival_time_max", sdt.format(sdt.parse(execution.getVariable("secondDateInterval").toString())))
						.setVariable("client_id", execution.getVariable("username")) 
						.setVariable("clientAddress", execution.getVariable("clientAddress"))
						.setVariable("cost", execution.getVariable("max_price"))
						.correlate();
				} else {
						
					execution.getProcessEngine().getRuntimeService().createMessageCorrelation("GetInterests")
						.setVariable("departure_airport", execution.getVariable("first_airport"))
						.setVariable("arrival_airport", execution.getVariable("second_airport"))	
						.setVariable("departure_time_min", sdt.format(sdt.parse(execution.getVariable("firstDate").toString())))
						.setVariable("departure_time_max", sdt.format(sdt.parse(execution.getVariable("firstDateInterval").toString())))
						.setVariable("client_id", execution.getVariable("username")) 
						.setVariable("clientAddress", execution.getVariable("clientAddress"))
						.setVariable("cost", execution.getVariable("max_price"))
						.correlate();
				
				}
				execution.removeVariable("customInterest");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else {
		
			t.username = "mariorossi".concat(String.valueOf(StaticValues.contatore_mario_rossi));	
			username = t.username;
			c.payment_username = "mariorossi".concat(String.valueOf(StaticValues.contatore_mario_rossi++));
			
			StaticValues.clienti.put(username, c);
			StaticValues.transazioni.add(t);
			
			RuntimeService runtimeService = execution.getProcessEngine().getRuntimeService();
			
			//genera la prima data e un intervallo di tempo casualmente; poi calcola la seconda data
			int year = 2021;
			int min_month = 0;
	        int max_month = 11;
	        int min_day = 1; 
	        int max_day = 31;
	        int min_period = 1; 
	        int max_period = 60;
	        
	        int month_range = max_month - min_month + 1;
	        int day_range = max_day - min_day + 1;
	        int period_range = max_period - min_period + 1; 
	  
	        // generate random numbers within 1 to 10
	        Calendar calendar = Calendar.getInstance();
	        Date firstDate, secondDate;
	        calendar.setLenient(false);
	        while(true) {
	        	try {
		            firstDate = calendar.getTime();
		            
		            int rand_period = (int)(Math.random() * period_range) + min_period;
		            calendar.add(Calendar.DATE, rand_period);
		            secondDate = calendar.getTime();
		            
		            break;
		            
	        	} catch(Exception e ) {
	        		continue;
	        	}
	
	        }
			
			//genera un prezzo massimo
	        //genera prezzi tra 200 e 1000
			int max_price = (int)(Math.random() * 800)+200;
	        
			//prendi due aeroporti a caso
			int first_airport_index = (int)(Math.random()*airports.length), second_airport_index;
			
			do {
				second_airport_index = (int)(Math.random()*airports.length);
			} while (second_airport_index == first_airport_index);
			
			String first_airport = airports[first_airport_index];
			String second_airport = airports[second_airport_index];
			
			//NOTA: date.getYear() restituisce l'anno, meno 1900. SIa maledetto chiunque sia l'impiegato alla sun che l'ha deciso
			calendar.set(firstDate.getYear()+1900, firstDate.getMonth(), firstDate.getDate());
			calendar.add(Calendar.DAY_OF_MONTH, 5);
			Date firstDateInterval = calendar.getTime();
			calendar.set(secondDate.getYear()+1900, secondDate.getMonth(), secondDate.getDate());
			calendar.add(Calendar.DAY_OF_MONTH, 5);
			Date secondDateInterval = calendar.getTime();
			
			//Scegliamo due date, una di arrivo e una di ripartenza; c'è una tolleranza di 5 giorni su entrambe
			//es. possiamo partire tra il 1 gennaio e il 6 gennaio, e ritornare tra il 20 gennaio e il 25 gennaio
			runtimeService.createMessageCorrelation("GetInterests")
				.setVariable("departure_airport", first_airport)
				.setVariable("arrival_airport", second_airport)
				.setVariable("departure_time_min", sdt.format(firstDate)) 
				.setVariable("departure_time_max", sdt.format(firstDateInterval))
				.setVariable("arrival_time_min", sdt.format(secondDate))
				.setVariable("arrival_time_max", sdt.format(secondDateInterval))
				.setVariable("client_id", t.username )
				.setVariable("clientAddress", "via indipendenza 1, bologna, italia")
				.setVariable("cost", max_price)
				.correlate();
			
		}
	
	
	}
}
