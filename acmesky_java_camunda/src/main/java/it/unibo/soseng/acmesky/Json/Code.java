package it.unibo.soseng.acmesky.Json;

import java.util.ArrayList;

public class Code {

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	public String getFly_code() {
		return fly_code;
	}

	public void setFly_code(String fly_code) {
		this.fly_code = fly_code;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}




	String code;
	String creation_date;
	String fly_code;
	String user;
	boolean sent; //booleano che indica se questo codice è già stato mandato all'utente - e quindi siamo in attesa di risposta dell'utente
	
	
	public Code() {
		
	}
}
