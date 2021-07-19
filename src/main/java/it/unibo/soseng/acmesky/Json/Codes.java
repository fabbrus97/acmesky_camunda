package it.unibo.soseng.acmesky.Json;

import java.util.ArrayList;

public class Codes {
	
	//codes.json ha questa forma:
	/* {codes: [
	 * 		{"code": "abcd", "creation_date": "01/01/2021", "fly_code": "1234", "users": ["mrossi", "mbianchi"] },
	 * 		{"code": "wxyz", "creation_date": "01/02/2021", "fly_code": "5678", "users": ["mrossi", "mneri"]},
	 * 		...
	 * ]}
	 * 
	 * */

	ArrayList<Code> codes; 
	
	public ArrayList<Code> getCodes() {
		return codes;
	}

	public void setCodes(ArrayList<Code> codes) {
		this.codes = codes;
	}

	public Codes() {
		
	}
	
}
