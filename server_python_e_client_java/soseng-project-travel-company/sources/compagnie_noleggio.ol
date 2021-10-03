include "compagnie_noleggio_interface.iol"
include "console.iol"
include "string_utils.iol"


inputPort NoleggioPort {
    Location: "socket://0.0.0.0:8000"
    Protocol: soap {
        .wsdl = "./prenotazione_trasporto.wsdl";
        .wsdl.port = "NoleggioPortServicePort";
        .dropRootValue = true
    }
    Interfaces: prenotazione_trasporto
}

cset{
    sid: dati_richiesta.sid
}

main {
    println@Console("Server avviato")();

    while(true){

        [registrazione(dati_registrazione)(response) {

            synchronized( id ){

                newuser = dati_registrazione.username;
                println@Console("Ricevuto username " + newuser)();
                //controlla che nome utente sia univoco
                ok = true
                response.text = "username creato"
                for (i = 0 , i < #global.users, i++){
                    println@Console("controllo " + global.users[i].user.username)()
                    if (newuser == global.users[i].user.username){
                        ok = false
                    }
                }
                if (!ok){
                    println@Console("Username esistente")()
                    response.text = "Username esistente"
                }
                else {

                    length@StringUtils( dati_registrazione.password )( pwdlength );
                    //controlla che password sia lunga almeno 6
                    if (pwdlength < 7 ){
                        println@Console("Password troppo corta")()
                        response.text = "Password troppo corta"
                    } else {

                        println@Console("Creo nuovo utente  " + newuser)();
                        response.text = "Nuovo utente creato"

                        //aggiungi nuovo utente
                        println@Console("#global.users: " + #global.users)()
                        global.users[#global.users].user.username = newuser;
                        println@Console("Salvo password " + dati_registrazione.password)()
                        println@Console("#global.users: " + #global.users)()
                        global.users[#global.users-1].user.password = dati_registrazione.password
                    }
                }
            }

        }]

        [login(dati_login)(nuovosid) {
            synchronized( id ){
                newuser = dati_login.username;
                println@Console("Ricevuto username  " + newuser)();
                println@Console("Ricevuta password  " + dati_login.password)();
                nuovosid.sid = ""
                println@Console("Controllo se l'utente è registrato")();
                if (#global.users > 0)
                    for (i = 0 , i < #global.users, i++){
                        println@Console("verifico se username corrisponde a " + global.users[i].user.username)()
                        if (newuser == global.users[i].user.username){
                            println@Console("verifico se la password corrisponde")()

                            if (dati_login.password == global.users[i].user.password){
                                nuovosid.sid = csets.sid = new
                                println@Console("Login effettuato con successo!")()
                            }
                        }
                    }



            }
        }]

        [richiesta(dati_richiesta)] {
            println@Console("Richiesta prenotazione compagnia di noleggio effettuata")()

        }

    }
}
