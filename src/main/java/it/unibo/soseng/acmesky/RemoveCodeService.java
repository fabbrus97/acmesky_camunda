package it.unibo.soseng.acmesky;

import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;

public class RemoveCodeService {

	public RemoveCodeService() {
		
	}
	
	public static void service(String code2delete) {
		Codes codes = GenerateCodesService.deserialize_file();
		codes.getCodes().removeIf( code -> code.getCode().equals(code2delete) );
		GenerateCodesService.serialize_json(codes);
	}
}
