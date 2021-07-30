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
}

interface prenotazione_trasporto {
    OneWay:
        richiesta(dati_richiesta)
}
