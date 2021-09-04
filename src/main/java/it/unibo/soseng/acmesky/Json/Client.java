package it.unibo.soseng.acmesky.Json;

import java.util.ArrayList;

public class Client {
	
	
	
	public ArrayList<Interest> interests; 
	String clientAddress;
	
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public ArrayList<Interest> getInterests() {
		return interests;
	}
	public void setInterests(ArrayList<Interest> interests) {
		this.interests = interests;
	}
	public Client() {
		
	}
}