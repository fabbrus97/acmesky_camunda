package it.unibo.soseng.acmesky;

public class RestoreInternalStateService {
	
	public RestoreInternalStateService() {
		
	}
	
	public static void service(String acmesky_code) {
		
		System.out.println("ACMESKY: ripristino stato interno");
		
		for (Transazione trans: StaticValues.transazioni ) {
			System.out.println(trans.username + " " + trans.acmesky_offer_code + " " + trans.paymentLink);
			System.out.println("-----------------------------------");
		}
		
		if (StaticValues.transazioni.removeIf( t -> t.acmesky_offer_code.contentEquals(acmesky_code))) {
			System.out.println("ACMESKY: Ho rimosso dei valori");
		} else {
			System.out.println("ACMESKY: Non ho rimosso dei valori");
		}
		System.out.println("Stato attuale transazioni: ");
		for (Transazione trans: StaticValues.transazioni ) {
			System.out.println(trans.username + " " + trans.acmesky_offer_code + " " + trans.paymentLink);
			System.out.println("-----------------------------------");
		}
		
	}
	
}
