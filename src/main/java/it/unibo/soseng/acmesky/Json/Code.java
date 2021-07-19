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

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	String code;
	String creation_date;
	String fly_code;
	ArrayList<String> users;
	
	public Code() {
		
	}
}
