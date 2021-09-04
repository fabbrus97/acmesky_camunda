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
import java.util.jar.Attributes.Name;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import it.unibo.soseng.acmesky.Json.Clients;
import it.unibo.soseng.acmesky.Json.Code;
import it.unibo.soseng.acmesky.Json.Codes;
import it.unibo.soseng.acmesky.Json.Flight;
import it.unibo.soseng.acmesky.Json.Interest;
import it.unibo.soseng.acmesky.Json.Offers;

public class FindMatchService {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private static DateTimeFormatter dtf_flights = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mma, 'CET'");
	private static HashMap<String, DepRetFlights> userFlights = new HashMap<String, DepRetFlights>();
	private static HashMap<String, ArrayList<Interest>> interests2delete = new HashMap<String, ArrayList<Interest>>(); //nome utente, interesse
	
	public FindMatchService() {
		
	}

	public static ArrayList<String[]> service() {
		
		/*
		 * Cerchiamo prima tutti i voli di andata compatibile (findInterests(true))
		 * facciamo una seconda passata per i voli di ritorno (findInterests(false))
		 * Se l'utente ha espresso interesse per un volo di ritorno, il volo esiste ed è compatibile,
		 * viene aggiunto alla lista dei voli di interesse di ritorno; se il volo non esiste, o non è compatibile,
		 * il volo di andata viene cancellato
		*/
		
		findInterests(true);
		findInterests(false);
		
		ArrayList<String[]> matches = new ArrayList<String[]>(); 
		
		userFlights.forEach((user, flights) -> {			
			
			
			(((DepRetFlights)flights).departureFlights).forEach(flight -> {
				
				
				System.out.println("Sto creando un match per l'utente " + user);
				System.out.println("Il volo è " + flight.getDepartureFrom() + " - " + flight.getDestination() + " (" + flight.getOfferCode() + ")");
				
				matches.add(new String[] {(String)user, flight.getOfferCode()});
			});
			
			(((DepRetFlights)flights).returnFlights).forEach(flight -> {
				
				System.out.println("Sto creando un match per l'utente " + user);
				System.out.println("Il volo è " + flight.getDepartureFrom() + " - " + flight.getDestination() + " (" + flight.getOfferCode() + ")");
				
				matches.add(new String[] {(String)user, flight.getOfferCode()});
			});
			
			
		});
		
		//ora che abbiamo generato i match, rimuoviamo gli interessi altrimenti acmesky li rimanda sempre
		Clients clients = SaveInterestService.deserialize_file();
		clients.getClients().forEach((name, client) -> {
			client.getInterests().removeAll(interests2delete.get(name));
			clients.getClients().put(name, client);
		});
		SaveInterestService.serialize_json(clients);
		
		return matches;
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
								ArrayList<Interest> tmp;
								if (interests2delete.containsKey(name)) {
									tmp = interests2delete.get(name);
									tmp.add(interest);
									
								} else {
									tmp = new ArrayList<Interest>();
									tmp.add(interest);
								}
								interests2delete.put(name, tmp);
								
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
										
										if (((DepRetFlights)userFlights.get(name)) != null ) {
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
															ArrayList<Interest> tmp = interests2delete.get(name);
															tmp.remove(interest);
															interests2delete.put(name, tmp);
															
															flights2remove.add(departure_flight);
															
														}
													}
												}
												 
											});
											((DepRetFlights)userFlights.get(name)).departureFlights.removeAll(flights2remove);
										}
										
										
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

		
}

class DepRetFlights {
	
	ArrayList<Flight> departureFlights;  
	ArrayList<Flight> returnFlights;
	
	public DepRetFlights() {
		departureFlights = new ArrayList<Flight>();
		returnFlights = new ArrayList<Flight>();
	}
	
}
