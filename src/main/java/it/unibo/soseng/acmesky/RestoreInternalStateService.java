package it.unibo.soseng.acmesky;

public class RestoreInternalStateService {
	
	public RestoreInternalStateService() {
		
	}
	
	public static void service(String username, String flight_code) {
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_offer_code.contentEquals(flight_code));
		
	}
	
}
