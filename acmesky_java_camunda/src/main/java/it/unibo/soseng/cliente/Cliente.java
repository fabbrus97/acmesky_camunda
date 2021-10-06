package it.unibo.soseng.cliente;

import java.util.ArrayList;

public class Cliente {
	String name; 
	//private ArrayList<String> offer_codes;
	String payment_username;
	String payment_password;
	String payment_token;
	
	public Cliente(String username) {
		name = username;
	}
	
}
