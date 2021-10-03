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
	private static HashMap<String, DepRetFlights> userFlights = new HashMap<String, DepRetFlights>(); //nomi utente, <hash interesse, voli>
	private static HashMap<String, ArrayList<Interest>> interests2delete = new HashMap<String, ArrayList<Interest>>(); //nome utente, interesse
	
	public FindMatchService() {
		
	}

	public static ArrayList<String[]> service() {
		
		/*
		 * Cerchiamo prima tutti i voli di andata compatibile (findInterests(true))
		 * facciamo una seconda passata per i voli di ritorno (findInterests(false)).
		 * I voli compatibili trovati vengono associati all'interesse dell'utente.
		 * A questo punto, per ogni interesse sia di andata che ritorno, controlliamo 
		 * che tra i voli associati vi sia appunto almeno un volo di andata e di ritorno.
		 * 
		*/
		
		Clients clients = SaveInterestService.deserialize_file();
		if (clients != null && clients.getClients() != null) {
			findInterests(true);
			findInterests(false);
		}
		ArrayList<String[]> matches = new ArrayList<String[]>(); 
		
		userFlights.forEach((user, flights) -> {			
			
			
			(((DepRetFlights)flights).flights).values().forEach(flightList -> {
				flightList.forEach(flight -> {
					System.out.println("Sto creando un match per l'utente " + user);
					System.out.println("Il volo è " + flight.getDepartureFrom() + " - " + flight.getDestination() + " (" + flight.getOfferCode() + ")");
				
					matches.add(new String[] {(String)user, flight.getOfferCode()});
				});
				
				
			});
			
			
		});
		
		//ora che abbiamo generato i match, rimuoviamo gli interessi altrimenti acmesky li rimanda sempre
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
				
				if (checkDeparture) { //ottimizzazione
					if (userFlights.containsKey(name)) {
						System.out.println("userFlights contiene lo username " + name);
						
						if (!((DepRetFlights)userFlights.get(name)).flights.containsKey(interest.myHashCode())) {
							System.out.println("userFlights !!!! Inserisco nuovo interesse nella hashmap per utente " + name + ": " + interest.getDeparture_airport() + " - " + interest.getArrival_airport());
							((DepRetFlights)userFlights.get(name)).flights.put(interest.myHashCode(), new ArrayList<Flight>());
						}
	
					} else {
							DepRetFlights dpf = new DepRetFlights();
							dpf.flights.put(interest.myHashCode(), new ArrayList<Flight>());
							userFlights.put(name, dpf);
							System.out.println("userFlights !! Aggiungo nuovo utente " + name);
					}
				}
				
				
				o.getOffers().forEach((companyName, flights) -> {
					
					flights.forEach(flight -> {
						
						 
						boolean isPriceOk = flight.getPrice().getAmount() <= interest.getCost();

						//controlliamo se troviamo il volo compatibile come andata - prima passata
						if (checkDeparture) {
							
							LocalDateTime departure_min = LocalDateTime.parse(interest.getDeparture_time_min(), dtf);
							LocalDateTime departure_max = LocalDateTime.parse(interest.getDeparture_time_max(), dtf);
							LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
														
							boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getDeparture_airport());
							boolean isDestinationOk = flight.getDestination().equals(interest.getArrival_airport());
							boolean isArrivalOk = within(departure_min, departure_max, takeoff);
							
							if (isArrivalOk && isPriceOk && isDepartureOk && isDestinationOk) {
								System.out.println("Ho trovato un volo di andata compatibilie per l'utente " + name + " e il volo del " + departure_min + ", " + departure_max + ", " + interest.getCost());
								System.out.println("Ho aggiunto il volo " + flight.getOfferCode() + " all'interesse " + interest.getDeparture_airport() + " - " + interest.getArrival_airport() + " per l'utente " + name);
								((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).add(flight);
								
							}

						}
						
						//controlliamo se troviamo il volo compatibile come ritorno - seconda passata
						if (!checkDeparture) {
							if (interest.getReturnHome_time_min() != null && !interest.getReturnHome_time_min().isEmpty()) {
															
								if (flight.getDepartureFrom().equals(interest.getArrival_airport())) {
									if (flight.getDestination().equals(interest.getDeparture_airport())) {
										
										LocalDateTime returnHome_min = LocalDateTime.parse(interest.getReturnHome_time_min(), dtf);
										LocalDateTime returnHome_max = LocalDateTime.parse(interest.getReturnHome_time_max(), dtf);
										LocalDateTime takeoff = LocalDateTime.parse(flight.getTakeoff(), dtf_flights);
										
										boolean isReturnHomeOk = within(returnHome_min, returnHome_max, takeoff);
										boolean isDepartureOk = flight.getDepartureFrom().equals(interest.getArrival_airport());
										boolean isDestinationOk = flight.getDestination().equals(interest.getDeparture_airport());
										
										boolean isReturnPriceOk = flight.getPrice().getAmount() + flight.getPrice().getAmount() <= interest.getCost();  
										
										if (isReturnHomeOk && isReturnPriceOk && isDepartureOk && isDestinationOk) {
											
											System.out.println("Ho trovato un volo di ritorno compatibile per l'utente " + name + " e il volo del " + returnHome_min + ", " + returnHome_max + ", " + interest.getCost());
											
											System.out.println("Trovato volo di ritorno (" + flight.getOfferCode() + ")! Infatti partenza volo " + flight.getDepartureFrom() + " ritorno cliente " + interest.getArrival_airport());
											System.out.println("Arrivo volo " + flight.getDestination() + " andata cliente " + interest.getDeparture_airport());
											
											((DepRetFlights)userFlights.get(name)).flights.get(interest.myHashCode()).add(flight);
											
										}
									}
								}
								
							} 
						}
					});
				}); 				
			});
			
		});
		
		if (!checkDeparture) { //controllo necessario altrimenti verebbe chiamato 2 volte
			System.out.println("ACMESKY: inizio la terza passata per eliminare voli andata-ritorno senza ritorno (o che costano troppo)");
			userFlights.forEach( (username, depRetFlights) -> {
				depRetFlights.flights.forEach( (hashInteresse, voli) -> {
					//voli è una lista di voli potenzialmente compatibili con un interesse; qui però abbiamo solo l'ash dell'interesse,
					//che è una stringa, bisogna risalire all'oggetto originale:
					clients.getClients().get(username).interests.forEach( (interest) -> {
						if (interest.myHashCode().contentEquals(hashInteresse)) {
							//ora cerchiamo se l'interesse ha un volo di ritorno (altrimenti tutti i voli qua presenti sono
							//già compatibili al 100%
							if (interest.getReturnHome_time_min() != null && !interest.getReturnHome_time_min().isEmpty()) {
								System.out.println("A questo interesse serve anche un volo di ritorno");
								ArrayList<Flight> andata = new ArrayList<Flight>();
								ArrayList<Flight> ritorno = new ArrayList<Flight>();
								for(Flight voloAndata: voli) {
									for(Flight voloRitorno: voli) {
										if (voloAndata != voloRitorno) {
											//sappiamo che i tempi sono già compatibili, controlliamo solo che sia un volo di ritorno e la somma dei prezzi vada bene
											if (voloAndata.getDepartureFrom().contentEquals(voloRitorno.getDestination()) && 
													voloAndata.getDestination().contentEquals(voloRitorno.getDepartureFrom())) {
												if (voloAndata.getPrice().getAmount() + voloRitorno.getPrice().getAmount() <= interest.getCost()) {
													//Aggiungiamo il volo di andata alla lista dei voli di andata compatibili con l'interesse
													if (!andata.contains(voloAndata) && !ritorno.contains(voloAndata)) { //non vogliamo aggiungere lo stesso volo 2 volte, e non vogliamo aggiungere
														//voli che sono segnati anche come voli di ritorno
														System.out.println("Aggiungo volo " + voloAndata.getOfferCode() + " ai voli d'andata per l'utente " + username);
														andata.add(voloAndata);
													}
													//Aggiungiamo il volo di ritorno alla lista dei voli di ritorno compatibili con l'interesse
													if (!ritorno.contains(voloRitorno) && !andata.contains(voloRitorno)) {
														System.out.println("Aggiungo volo " + voloRitorno.getOfferCode() + " ai voli di ritorno per l'utente " + username);
														ritorno.add(voloRitorno);
													}
													//L'interesse è soddisfatto da almeno un volo, si può rimuovere  												
													break;
												}
											}
										}
									}
								}
								
								System.out.println("userFlights libero la lista degli aerei di " + username + " per " + interest.getDeparture_airport() + " - " +interest.getArrival_airport() + " (" + ((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).size() +  ")");
								((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).clear();
								((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).addAll(andata);
								System.out.println("userFlights Dimensione attuale (1/2): " + ((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).size());
								((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).addAll(ritorno);
								System.out.println("userFlights Dimensione attuale (2/2): " + ((DepRetFlights)userFlights.get(username)).flights.get(interest.myHashCode()).size());
									
								if (interests2delete.containsKey(username))
									interests2delete.get(username).add(interest);
								else {
									ArrayList<Interest> newInterestList = new ArrayList<Interest>();
									newInterestList.add(interest);
									interests2delete.put(username, newInterestList);
								}
	
							} else {
								if (voli.size() > 0) {
									//possiamo cancellare l'interesse perché sappiamo che se c'è almeno un volo, è sicuramente compatibile
									System.out.println("ACMESKY: aggiungo l'interesse tra quelli da eliminare");
									if (interests2delete.containsKey(username))
										interests2delete.get(username).add(interest);
									else {
										ArrayList<Interest> newInterestList = new ArrayList<Interest>();
										newInterestList.add(interest);
										interests2delete.put(username, newInterestList);
									}
								}
							}
						}
					});
				});
			});
		}
		
	}
	
	private static boolean within(LocalDateTime start, LocalDateTime end, LocalDateTime test) {	
		return start.isBefore(test) && end.isAfter(test); 
	}

		
}

class DepRetFlights {
	
	HashMap<String, ArrayList<Flight>> flights;
	
	//ArrayList<Flight> departureFlights;
	//ArrayList<Flight> finalDepartureFlights;
	//ArrayList<Flight> returnFlights;
	
	public DepRetFlights() {
		flights = new HashMap<String, ArrayList<Flight>>(); //hashcode interesse, voli
		//departureFlights = new ArrayList<Flight>();
		//returnFlights = new ArrayList<Flight>();
	}
	
}
