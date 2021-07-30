package it.unibo.soseng.acmesky;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.Offers;

public class GenerateCodesService {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private static DateTimeFormatter dtf_flights = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mma, 'CET'");
	private static HashMap userFlights = new HashMap<String, DepRetFlights>();
	
	public GenerateCodesService() {
		
	}


	public static void service() {
		
		/*
		 * Cerchiamo prima tutti i voli di andata compatibile (findInterests(true))
		 * facciamo una seconda passata per i voli di ritorno (findInterests(false))
		 * Se l'utente ha espresso interesse per un volo di ritorno, il volo esiste ed è compatibile,
		 * viene aggiunto alla lista dei voli di interesse di ritorno; se il volo non esiste, o non è compatibile,
		 * il volo di andata viene cancellato
		*/
		
		findInterests(true);
		findInterests(false);
		
		userFlights.forEach((user, flights) -> {			
			
			((ArrayList<Flight>)flights).forEach(flight -> {
				save_new_code(user.toString(), flight.getOfferCode());
			});
			
			
		});
		
		
	}
	
	private static void findInterests(boolean checkDeparture) {
		Offers o = GetOffersService.getJSON();
		Clients clients = SaveInterestService.deserialize_file();
		
				
		clients.getClients().forEach( (name, client) -> {

			client.getInterests().forEach(interest -> {
				
				ArrayList<Flight> flight2delete4returnNotFound = new ArrayList<Flight>(); //lista dei voli da cancellare perché non esiste il volo di ritorno
				
				o.getOffers().forEach((companyName, flights) -> {
					
					flights.forEach(flight -> {
						
						flight2delete4returnNotFound.add(flight);
						 
						boolean isPriceOk = flight.getPrice().getAmount() <= interest.getCost();
						System.out.println("Cerco match...");
						//controlliamo se il volo di andata è compatibile
						if (checkDeparture) {
							LocalDateTime departure_min = LocalDateTime.parse(interest.getDeparture_time_min(), dtf);
							LocalDateTime departure_max = LocalDateTime.parse(interest.getDeparture_time_max(), dtf);
							LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
														
							boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getDeparture_airport());
							boolean isDestinationOk = flight.getDestination().equals(interest.getArrival_airport());
							boolean isArrivalOk = within(departure_min, departure_max, takeoff);
							
							if (isArrivalOk && isPriceOk && isDepartureOk && isDestinationOk) {
								if (userFlights.containsKey(name)) {
									((DepRetFlights)userFlights.get(name)).departureFlights.add(flight);
								} else {
									DepRetFlights dpf = new DepRetFlights();
									dpf.departureFlights.add(flight);
									userFlights.put(name, dpf);
								}
							}

						}
						
						
						//controlliamo se il volo di ritorno è compatibile
						if (!checkDeparture) {
							if (interest.getReturnHome_time_min() != null) {
							
								
								
								if (flight.getDepartureFrom().equals(interest.getArrival_airport())) {
									if (flight.getDestination().equals(interest.getDeparture_airport())) {
										flight2delete4returnNotFound.remove(flight);
										
										ArrayList<Flight> flights2remove = new ArrayList<Flight>();
										
										((DepRetFlights)userFlights.get(name)).departureFlights.forEach( departure_flight -> {			
											
											if (departure_flight.getDepartureFrom().equals(flight.getDestination())) {
												if (departure_flight.getDestination().equals(flight.getDepartureFrom())) {
											
													LocalDateTime returnHome_min = LocalDateTime.parse(interest.getReturnHome_time_min(), dtf);
													LocalDateTime returnHome_max = LocalDateTime.parse(interest.getReturnHome_time_max(), dtf);
													LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
													
													boolean isReturnHomeOk = within(returnHome_min, returnHome_max, takeoff);
													boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getArrival_airport());
													boolean isDestinationOk = flight.getDestination().equals(interest.getDeparture_airport());
													
													boolean isPriceReallyOk = departure_flight.getPrice().getAmount() + flight.getPrice().getAmount() <= interest.getCost();  
													
													if (isReturnHomeOk && isPriceReallyOk && isDepartureOk && isDestinationOk) {
														((DepRetFlights)userFlights.get(name)).returnFlights.add(flight);
													} else {
														flights2remove.add(departure_flight);
													}
												}
											}
											 
										});
										
										((DepRetFlights)userFlights.get(name)).departureFlights.removeAll(flights2remove);
									}
								}
								
							} else {
								flight2delete4returnNotFound.remove(flight);
							}
						}
					});
				}); 
				
				if (!checkDeparture) {
					if ((DepRetFlights)userFlights.get(name) != null) {
						((DepRetFlights)userFlights.get(name)).departureFlights.removeAll(flight2delete4returnNotFound);
					} else {
						System.out.println("Perdonaci Signore perché non sappiamo quello che facciamo");
					}
					
				}
				
			});
			
			

		});
		
	}
	
	private static boolean within(LocalDateTime start, LocalDateTime end, LocalDateTime test) {
		return (start.isBefore(test) && end.isAfter(test)); 
	}

	private static void save_new_code(String user, String fly_code) {

		Codes c  = deserialize_file();
		boolean habemus_codex;
		String new_random_code; 
		while(true) {
			habemus_codex = true;
			//genera 6 caratteri alfanumerici random
			new_random_code = "";
			//controlla se esistono già
			for (Code code: c.getCodes()) {
				if (code.getCode() == new_random_code) {
					LocalDateTime creation_date = LocalDateTime.parse(code.getCreation_date(), dtf);
					LocalDateTime now = LocalDateTime.now();
					Duration duration = Duration.between(creation_date.toLocalDate(), now.toLocalDate());
					if (duration.toHours() < 24) {
						habemus_codex = false;
						break;
					}
					else  { //abbiamo trovato un codice scaduto, lo possiamo "cancellare" e "riaggiungere"
						//habemus_codex = true è già vero
						c.getCodes().remove(code); 
					}
				}
			}
			
			if (habemus_codex)
				break; 
		}
		//salva la lista
		Code new_code = new Code();
		new_code.setCode(new_random_code);
		new_code.setFly_code(fly_code);
		new_code.setUser(user);
		  
		LocalDateTime now = LocalDateTime.now();  
  		new_code.setCreation_date(dtf.format(now));
		c.getCodes().add(new_code);
		serialize_json(c);
	}
	
	protected static Codes deserialize_file() {
		Codes c = null;
		File file = new File(StaticValues.codes_file_path);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream fs = new FileOutputStream(file);
				fs.write("{ \"codes\": [] }".getBytes());
				fs.flush(); fs.close();

			}
				
			Gson j = new Gson();
			FileReader fileReader = new FileReader(file);
			c = j.fromJson(fileReader, Codes.class);
			
			fileReader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return c; 
	}
	
	protected static void serialize_json(Codes codes) {
		try {
			Gson j = new Gson();
			Writer writer;
			writer = new FileWriter(new File(StaticValues.codes_file_path));
			JsonWriter jsonWriter = new JsonWriter(writer);
			
			j.toJson(codes, Codes.class, jsonWriter);
			
			jsonWriter.flush();
			System.out.println("Fatto");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class DepRetFlights {
	
	ArrayList<Flight> departureFlights;  
	ArrayList<Flight> returnFlights;
	
	public DepRetFlights() {
		departureFlights = new ArrayList<Flight>();
		returnFlights = new ArrayList<Flight>();
	}
	
}
