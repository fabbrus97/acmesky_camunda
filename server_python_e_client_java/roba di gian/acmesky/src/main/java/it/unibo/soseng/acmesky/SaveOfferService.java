package it.unibo.soseng.acmesky;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import io.swagger.client.model.InlineResponse2001;

public class SaveOfferService {
	private final static String path = "/public/simonef/testtest.txt" ; 
	
	public SaveOfferService() {
	}
	
	public static void service(InlineResponse2001 offers) {
		File file = new File(path);
		
			try {
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter w = new FileWriter(file);
				
				BufferedWriter writer = new BufferedWriter(w);
				
				for (int i = 0; i < 10; i++) {
					writer.append(i + " - The quick brown fox jumps over the lazy dog\n");
					Thread.sleep(1000);
				}
				
				writer.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
}

class Airline { //TODO muovi nella cartella Json
	Map<String, Flights> airline;

	public Map<String, Flights> getAirline() {
		return airline;
	}
	public void setClients(Map<String, Flights> airline) {
		this.airline = airline;
	}
	Airline() {

	}

}

class Flights {
	ArrayList<Flight> flights;

	public ArrayList<Flight> getFlights() {
		return flights;
	}
	public void setInterests(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	Flights() {

	}
}

class Flight{
	String departure_airport ;
	String arrival_airport ;
	String takeoff;
	Price price;


	public String getDeparture_airport() {
		return departure_airport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}

	public String getArrival_airport() {
		return arrival_airport;
	}

	public void setArrival_airport(String arrival_airport) {
		this.arrival_airport = arrival_airport;
	}

	public String getTakeoff() {
		return takeoff;
	}

	public void setTakeoff(String takeoff) {
		this.takeoff = takeoff;
	}

	public Price getPrice(){
		return price;
	}

	public void setPrice(Price price){
		this.price = price;
	}

	Flight() {

	}
}

class Price{

	int amount;
	String currency;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	Price() {
	}
}
