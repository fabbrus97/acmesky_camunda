package it.unibo.soseng.acmesky.Json;

public class Interest{
	String departure_airport ;
	String arrival_airport ;
	String departure_time_min; 
	String departure_time_max;
	String returnHome_time_min;
	String returnHome_time_max;
	
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
	
	
	public String getReturnHome_time_min() {
		return returnHome_time_min;
	}

	public void setReturnHome_time_min(String returnHome_time_min) {
		this.returnHome_time_min = returnHome_time_min;
	}

	public String getReturnHome_time_max() {
		return returnHome_time_max;
	}

	public void setReturnHome_time_max(String returnHome_time_max) {
		this.returnHome_time_max = returnHome_time_max;
	}


	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public Interest() {
		
	}
	
	public String myHashCode() {
		return this.departure_airport + this.arrival_airport + this.departure_time_min + this.returnHome_time_min + String.valueOf(this.cost);
	}
	
	
}
