package it.unibo.soseng.acmesky.Json;

public class Interest{
	String departure_airport ;
	String arrival_airport ;
	String departure_time_min; 
	String departure_time_max;
	String arrival_time_min;
	String arrival_time_max;
	int cost;
	
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

	public String getDeparture_time_min() {
		return departure_time_min;
	}

	public void setDeparture_time_min(String departure_time_min) {
		this.departure_time_min = departure_time_min;
	}

	public String getDeparture_time_max() {
		return departure_time_max;
	}

	public void setDeparture_time_max(String departure_time_max) {
		this.departure_time_max = departure_time_max;
	}

	public String getArrival_time_min() {
		return arrival_time_min;
	}

	public void setArrival_time_min(String arrival_time_min) {
		this.arrival_time_min = arrival_time_min;
	}

	public String getArrival_time_max() {
		return arrival_time_max;
	}

	public void setArrival_time_max(String arrival_time_max) {
		this.arrival_time_max = arrival_time_max;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	
	public Interest() {
		
	}
}
