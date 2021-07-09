package it.unibo.soseng.acmesky;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import io.swagger.client.model.InlineResponse2001;

public class SaveOfferService {
	private final static String path = "/public/simonef/testtest.txt" ; 
	
	public SaveOfferService() {
	}
	
	public static void service(InlineResponse2001 offers) {
		File file = new File(path);
		
			try {
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter w = new FileWriter(file);
				
				BufferedWriter writer = new BufferedWriter(w);
				
				for (int i = 0; i < 10; i++) {
					writer.append(i + " - The quick brown fox jumps over the lazy dog\n");
					Thread.sleep(1000);
				}
				
				writer.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
}
