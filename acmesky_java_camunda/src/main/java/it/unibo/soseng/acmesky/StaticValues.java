package it.unibo.soseng.acmesky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

public class StaticValues {
	
	private static String acmesky_username = "acmesky";
	private static String acmesky_password = "12345678";
	
	public static String airline_username = acmesky_username;
	public static String airline_password = acmesky_password;
	public static String airline_token = "";
	public static String distance_username = acmesky_username;
	public static String distance_password = acmesky_password;
	public static String distance_token = "";
	public static String prontogram_key = "";
	public static String payment_provider_key = "";
	public static String prontogram_username = acmesky_username;
	public static String prontogram_password = acmesky_password;
	public static String offers_file_path = "offers.json";
	public static String client_interests_file_path = "client_interests.json";
	public static String codes_file_path = "codes.json";
	public static String token_compagnia_trasporti = "";
	public static LinkedList<Transazione> transazioni = new LinkedList<Transazione>();
	
	/** Server list: **/
	public static String paymentUrl = "";
	public static HashMap<String, String> codes2delete = new HashMap<String, String>(); //payment_link, code2delete
	public static String geoproviderUrl = "";
	public static String prontogramUrl = "";
	public static ArrayList<String[]> transports = new ArrayList<String[]>(); //lista di <url, indirizzo>
	public static ArrayList<String> airlinesUrl = new ArrayList<String>();

	
}
