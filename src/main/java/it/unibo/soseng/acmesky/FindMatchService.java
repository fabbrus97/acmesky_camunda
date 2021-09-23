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
//import it.unibo.soseng.acmesky.Json.Codes;
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
		
		Clients clients = SaveInterestService.deserialize_file();
		if (clients != null && clients.getClients() != null) {
			findInterests(true);
			findInterests(false);
		}
		ArrayList<String[]> matches = new ArrayList<String[]>(); 
		
		userFlights.forEach((user, flights) -> {			
			
			
			//(((DepRetFlights)flights).departureFlights).forEach(flight -> {
			(((DepRetFlights)flights).flights).values().forEach(flightList -> {
				flightList.forEach(flight -> {
					System.out.println("Sto creando un match per l'utente " + user);
					System.out.println("Il volo è " + flight.getDepartureFrom() + " - " + flight.getDestination() + " (" + flight.getOfferCode() + ")");
				
					matches.add(new String[] {(String)user, flight.getOfferCode()});
				});
				
				
			});
			
			/*(((DepRetFlights)flights).returnFlights).forEach(flight -> {
				
				System.out.println("Sto creando un match per l'utente " + user);
				System.out.println("Il volo è " + flight.getDepartureFrom() + " - " + flight.getDestination() + " (" + flight.getOfferCode() + ")");
				
				matches.add(new String[] {(String)user, flight.getOfferCode()});
			});*/
			
			
		});
		
		//ora che abbiamo generato i match, rimuoviamo gli interessi altrimenti acmesky li rimanda sempre
		//TODO non funziona
		//Clients clients = SaveInterestService.deserialize_file();
		clients.getClients().forEach((name, client) -> {
			if (interests2delete.get(name) != null) {
				//if (client.getInterests().removeAll(interests2delete.get(name))) {
				for (Interest i : interests2delete.get(name)) {
					if (client.getInterests().removeIf( intrs -> intrs.myHashCode().equals(i.myHashCode()))) {
						System.out.println("ACMESKY: ho cancellato l'interesse dell'utente " + name + " " + i.myHashCode());
						
					} else {
						System.out.println("ACMESKY: non ho cancellato l'interesse dell'utente " + name + " " + i.myHashCode());
					}
				}
				clients.getClients().put(name, client);
			}
			
		});
		SaveInterestService.serialize_json(clients);
		
		userFlights.clear();
		interests2delete.clear();
		
		return matches;
	}
	
	private static void findInterests(boolean checkDeparture) {
		Offers o = GetOffersService.getJSON();
		Clients clients = SaveInterestService.deserialize_file();
		
		clients.getClients().forEach( (name, client) -> {

			client.getInterests().forEach(interest -> {
				if (userFlights.containsKey(name)) {
					
					if (!((DepRetFlights)userFlights.get(name)).flights.containsKey(interest.myHashCode())) {
						System.out.println("!!!! Inserisco nuovo interesse nella hashmap per utente " + name + ": " + interest.getDeparture_airport() + " - " + interest.getArrival_airport());
						((DepRetFlights)userFlights.get(name)).flights.put(interest.myHashCode(), new ArrayList<Flight>());
					}

				} else {
						DepRetFlights dpf = new DepRetFlights();
						dpf.flights.put(interest.myHashCode(), new ArrayList<Flight>());
						userFlights.put(name, dpf);
						System.out.println("!! Aggiungo nuovo utente " + name);
				}
				
				
				ArrayList<Flight> flight2delete4returnNotFound = new ArrayList<Flight>(); //lista dei voli da cancellare perché non esiste il volo di ritorno
				
				//System.out.println("Cerco interesse per cliente " + name + " e il volo del tipo " + interest.getDeparture_time_min() + ", " + interest.getDeparture_time_max() + ", " + interest.getCost() + ", " + interest.getDeparture_airport() + " - " + interest.getArrival_airport());
				
				o.getOffers().forEach((companyName, flights) -> {
					
					flights.forEach(flight -> {
						
						//System.out.println("Vediamo se questo volo soddisfa i requisiti: " + flight.getTakeoff() + ", " + flight.getPrice().getAmount() + ", " + flight.getDepartureFrom() + " - " + flight.getDestination());
						
						flight2delete4returnNotFound.add(flight);
						 
						boolean isPriceOk = flight.getPrice().getAmount() <= interest.getCost();

						//controlliamo se il volo di andata è compatibile
						if (checkDeparture) {
							
							LocalDateTime departure_min = LocalDateTime.parse(interest.getDeparture_time_min(), dtf);
							LocalDateTime departure_max = LocalDateTime.parse(interest.getDeparture_time_max(), dtf);
							LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
														
							boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getDeparture_airport());
							boolean isDestinationOk = flight.getDestination().equals(interest.getArrival_airport());
							boolean isArrivalOk = within(departure_min, departure_max, takeoff);
							
							if (isArrivalOk && isPriceOk && isDepartureOk && isDestinationOk) {
								System.out.println("Ho trovato un volo di andata compatibilie per l'utente " + name + " e il volo del " + departure_min + ", " + departure_max + ", " + interest.getCost());
								ArrayList<Interest> tmp;
								if (interests2delete.containsKey(name)) {
									tmp = interests2delete.get(name);
									tmp.add(interest);
									
								} else {
									tmp = new ArrayList<Interest>();
									tmp.add(interest);
								}
								interests2delete.put(name, tmp);
								
								System.out.println("Ho aggiunto il volo " + flight.getOfferCode() + " all'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " per l'utente " + name);
								((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).add(flight);
								
								((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).forEach( departure_flight -> {
									System.out.println(departure_flight.getOfferCode());
								});
								
								//if (userFlights.containsKey(name)) {
									 
								//	System.out.println("Ho aggiunto a userFlights l'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " per l'utente " + name);
									
									/*if (interest.getReturnHome_time_min() != null && !interest.getReturnHome_time_min().isEmpty())
										((DepRetFlights)userFlights.get(name)).finalDepartureFlights.add(flight);
									else
										((DepRetFlights)userFlights.get(name)).departureFlights.add(flight);
									*/
								//} else {
									//if (interest.getReturnHome_time_min() != null && !interest.getReturnHome_time_min().isEmpty()) {
									/*	DepRetFlights dpf = new DepRetFlights();
										//dpf.finalDepartureFlights.add(flight);
										dpf.flights.put(interest, new ArrayList<Flight>());
										dpf.flights.get(interest).add(flight);
										userFlights.put(name, dpf);
										
										System.out.println("Ho aggiunto a userFlights l'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " per l'utente " + name);
									*/	
									/*} else {
										DepRetFlights dpf = new DepRetFlights();
										dpf.departureFlights.add(flight);
										userFlights.put(name, dpf);
									}*/
								//}
								
							}

						}
						
						
						//controlliamo se il volo di ritorno è compatibile
						if (!checkDeparture) {
							if (interest.getReturnHome_time_min() != null && !interest.getReturnHome_time_min().isEmpty()) {
							
								//System.out.println("ACMESKY: devo cercare un volo di ritorno perché getReturnHome_time_min per " + name + " vale " + interest.getReturnHome_time_min());
								
								if (flight.getDepartureFrom().equals(interest.getArrival_airport())) {
									if (flight.getDestination().equals(interest.getDeparture_airport())) {
										
										System.out.println("Trovato volo di ritorno (" + flight.getOfferCode() + ")! Infatti partenza volo " + flight.getDepartureFrom() + " ritorno cliente " + interest.getArrival_airport());
										System.out.println("Arrivo volo " + flight.getDestination() + " andata cliente " + interest.getDeparture_airport());
										
										flight2delete4returnNotFound.remove(flight);
										flight2delete4returnNotFound.removeIf( f -> f.getDepartureFrom().contentEquals(f.getDestination()) && f.getDestination().contentEquals(f.getDepartureFrom()));
										
										
										ArrayList<Flight> flights2remove = new ArrayList<Flight>();
										
										if (((DepRetFlights)userFlights.get(name)) != null ) {
											//((DepRetFlights)userFlights.get(name)).departureFlights.forEach( departure_flight -> {
											//System.out.println("Vediamo quali sono i voli di andata che l'utente " + name + " si è salvato per l'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " (" + ((DepRetFlights)userFlights.get(name)).flights.get(interest).size() + ")");
											((DepRetFlights)userFlights.get(name)).flights.keySet().forEach( key -> {
												System.out.println("Chiave: " + key);
												
												((DepRetFlights)userFlights.get(name)).flights.get(key).forEach( val -> {
													
													System.out.println("....Valore: " + val.getOfferCode());
													
												});
												
											});

											ArrayList<Flight> tmpFlights = new ArrayList<Flight>(); //creo una lista temporanea dei voli da aggiungere a 
											//userFlights.get(name)).flights; non posso aggiungerli direttamente dentro il forEach perché solleverebbe
											//l'eccezione java.util.ConcurrentModificationException
											((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).forEach( departure_flight -> {
												
												System.out.println(departure_flight.getOfferCode());
												
												if (departure_flight.getDepartureFrom().equals(flight.getDestination())) {
													if (departure_flight.getDestination().equals(flight.getDepartureFrom())) {
												
														flight2delete4returnNotFound.removeIf( f -> f.getOfferCode().contentEquals(departure_flight.getOfferCode()));
														
														System.out.println("Ma ora contiene");
														for (Flight f: flight2delete4returnNotFound) {
															System.out.println(f.getOfferCode());
														}
														
														LocalDateTime returnHome_min = LocalDateTime.parse(interest.getReturnHome_time_min(), dtf);
														LocalDateTime returnHome_max = LocalDateTime.parse(interest.getReturnHome_time_max(), dtf);
														LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
														
														boolean isReturnHomeOk = within(returnHome_min, returnHome_max, takeoff);
														boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getArrival_airport());
														boolean isDestinationOk = flight.getDestination().equals(interest.getDeparture_airport());
														
														boolean isPriceReallyOk = departure_flight.getPrice().getAmount() + flight.getPrice().getAmount() <= interest.getCost();  

														/*
														 * if (isReturnHomeOk) {
															System.out.println("returnHome ok");
														} else {
															System.out.println("returnHome non ok");
														}

														if (isPriceReallyOk) {
															System.out.println("priceReally ok");
														} else {
															System.out.println("priceReally non ok");
														}

														if (isDepartureOk) {
															System.out.println("departure ok");
														} else {
															System.out.println("departure non ok");
														}

														if (isDestinationOk) {
															System.out.println("destination ok");
														} else {
															System.out.println("destination non ok");
														}
														*/
														
														if (isReturnHomeOk && isPriceReallyOk && isDepartureOk && isDestinationOk) {
															
															System.out.println("Ho trovato un volo di ritorno compatibile per l'utente " + name + " e il volo del " + returnHome_min + ", " + returnHome_max + ", " + interest.getCost());
															
															//((DepRetFlights)userFlights.get(name)).returnFlights.add(flight);
															tmpFlights.add(flight);
															//((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).add(flight); 
														} else {
															System.out.println("Il volo " + departure_flight.getOfferCode() + " non è compatibile, lo aggiungo ai voli da rimuovere...");
															ArrayList<Interest> tmp = interests2delete.get(name);
															tmp.remove(interest);
															interests2delete.put(name, tmp);
															
															flights2remove.add(departure_flight);
															
														}
													}
												}
												 
											});
											((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).addAll(tmpFlights); 
											System.out.println("Cancello questi voli di andata, così de botto senza senso:");
											for (Flight f: flights2remove) {
												System.out.println(f.getOfferCode());
											}
											//((DepRetFlights)userFlights.get(name)).departureFlights.removeAll(flights2remove);
											((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).removeAll(flights2remove);
										}
										
										
									}
								}
								
							} else {
								System.out.println("Il cliente non è interessato al volo di ritorno, stiamo apposto");
								flight2delete4returnNotFound.remove(flight);
							}
						}
					});
				}); 
				
				if (!checkDeparture) {
					if ((DepRetFlights)userFlights.get(name) != null) {
						//System.out.println("Ok, la quest è finita, cancelliamo i voli di andata senza voli di ritorno (voli andata: " +  ((DepRetFlights)userFlights.get(name)).departureFlights.size() + ")");
						
						/*System.out.println("Voli di andata: ");
						ArrayList<Flight> depF = ((DepRetFlights)userFlights.get(name)).departureFlights;
						for (Flight f : depF) {
							System.out.println(f.getDepartureFrom() + " - " + f.getDestination() + ", " + f.getTakeoff() + " " + f.getPrice().getAmount());
						}*/
						
						//((DepRetFlights)userFlights.get(name)).departureFlights.removeAll(flight2delete4returnNotFound); //TODO non va
						
						System.out.println("Voli da cancellare: ");
						for (Flight f : flight2delete4returnNotFound) {
							System.out.println(f.getOfferCode());
							
							System.out.println("Togliamo da userFlights l'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " per l'utente " + name);
							
							if (
							//((DepRetFlights)userFlights.get(name)).departureFlights.removeIf( flight2check -> f.getOfferCode().contentEquals(flight2check.getOfferCode()))
							((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).removeIf( flight2check -> f.getOfferCode().contentEquals(flight2check.getOfferCode()))
							) {
								System.out.println("Ho cancellato la merda " + f.getOfferCode());
							} else {
								System.out.println("La merda " + f.getOfferCode() +  " è ancora lì");
							}
						}
						
						//System.out.println("Nuova dimensione voli andata: " +  ((DepRetFlights)userFlights.get(name)).departureFlights.size());
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
	
	HashMap<String, ArrayList<Flight>> flights;
	
	//ArrayList<Flight> departureFlights;
	//ArrayList<Flight> finalDepartureFlights;
	//ArrayList<Flight> returnFlights;
	
	public DepRetFlights() {
		flights = new HashMap<String, ArrayList<Flight>>();
		//departureFlights = new ArrayList<Flight>();
		//returnFlights = new ArrayList<Flight>();
	}
	
}
