package it.unibo.soseng.acmesky;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticValues {
	
	public static String airline_username = "";
	public static String airline_password = "";
	public static String airline_token = "";
	public static String distance_username = "";
	public static String distance_password = "";
	public static String distance_token = "";
	public static String prontogram_key = "";
	public static String payment_provider_key = "";
	public static String prontogram_username = "acmesky";
	public static String prontogram_password = "12345678";
	public static String offers_file_path = "offers.json";
	public static String client_interests_file_path = "client_interests.json";
	public static String codes_file_path = "codes.json";
	public static String token_compagnia_trasporti = "";
	/** Server list: **/
	public static String paymentUrl = "";
	public static HashMap<String, String> codes2delete = new HashMap<String, String>(); //payment_link, code2delete
	
}
