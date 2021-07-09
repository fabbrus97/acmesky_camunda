package it.unibo.soseng.acmesky;

import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

public class CheckCodeService {
	
	public CheckCodeService() {
		
	}
	
	public static boolean service(String code) {
		
		Codes codes = GenerateCodesService.deserialize_file();
		
		for (Code c: codes.getCodes()) {
			if (c.getCode().equals(code))
				return true;
		}
		
		return false;
		
	}

}
