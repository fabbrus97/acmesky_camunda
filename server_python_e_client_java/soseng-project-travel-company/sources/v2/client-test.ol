include "compagnie_noleggio_interface.iol"
include "console.iol"

outputPort NoleggioPort {
    Location: "socket://0.0.0.0:8000"
    Protocol: soap 
    Interfaces: prenotazione_trasporto 
}

main {
  
    
    dati_registrazione.username = "simone";
    dati_registrazione.password = "12345678";
    
    println@Console("Invio dati " + dati_registrazione.username  + " " + dati_registrazione.password)()
    registrazione@NoleggioPort(dati_registrazione)(sid);
    login@NoleggioPort(dati_registrazione)(sid);
    println@Console("Ricevuto sid " + sid)()
    
}