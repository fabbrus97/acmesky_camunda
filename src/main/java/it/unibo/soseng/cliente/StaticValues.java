package it.unibo.soseng.cliente;

import java.util.HashMap;
import java.util.LinkedList;

public class StaticValues {
	
	public static HashMap<String, Cliente> clienti = new HashMap<String, Cliente>();   //questi valori non si possono droppare
	
	public static LinkedList<Transazione> transazioni = new LinkedList<Transazione>(); //questi valori si possono droppare

	public static int contatore_mario_rossi = 1;
	
}