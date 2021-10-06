package it.unibo.soseng.acmesky;

public class RestoreInternalStateService {
	
	public RestoreInternalStateService() {
		
	}
	
	public static void service(String acmesky_code) {
		
		StaticValues.transazioni.removeIf( t -> t.acmesky_offer_code.contentEquals(acmesky_code));
		
	}
	
}
