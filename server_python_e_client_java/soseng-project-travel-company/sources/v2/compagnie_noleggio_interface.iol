type indirizzo:void {
    .via: string
    .civico: int
    .cap: int
    .comune: string
    .provincia: string
} 

type dati_richiesta:void{
    
    .luoghi:void {
        .partenza:indirizzo
        .arrivo:indirizzo
    }
    
    .data:void {
        .giorno: int
        .mese: int
        .anno: int
    }
    .ora: void {
        .hh: int
        .mm: int
    }

    .sid: string
}

type dati_registrazione:void {
    .username: string
    .password: string
}

type dati_login:void {
    .username: string
    .password: string
}

type sid: void {
    .sid: string
}

type response: void {
    .text: string
}

interface prenotazione_trasporto {
    OneWay:
        richiesta(dati_richiesta)
        
    RequestResponse:
        registrazione(dati_registrazione)(response),
        login(dati_login)(sid)
}