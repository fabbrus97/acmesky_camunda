import requests
import simpleCamundaRESTPost
import datetime
import random
import json
from datetime import date

compagnie = {
        "rayanair": "",
        "britishaw": "",
        "japanairl": "",
        "emirates": ""
    }
airports = ["BLQ", "BGY", "CTA", "MXP", "VRN", "FCO", "LGW", "FRA", "BCN", "LIS", "AUH", "SVO", "ORY"]

auth_header = {"authtoken": "token_hc_test"}

def inizializza_compagnie():
    f = open("airline.list", "r")
    for x in f:
        x = x.replace("\n", "")
        r = requests.get(x+"/flights")

        if "rayanair" in r.text:
            compagnie["rayanair"] = x
        if "britishaw" in r.text:
            compagnie["britishaw"] = x
        if "japanairl" in r.text:
            compagnie["japanairl"] = x
        if "emirates" in r.text:
            compagnie["emirates"] = x


def insert_data():

    while True:
        correctDate = False
        giorno = input("Giorno:")
        if giorno == 'e' or giorno == 'E':
            return ""
        mese = input("Mese:")
        if mese == 'e' or mese == 'E':
            return ""
        anno = input("Anno:")
        if anno == 'e' or anno == 'E':
            return ""
        ora = input("Ora:")
        if ora == 'e' or ora == 'E':
            return ""
        minuti = input("Minuti:")
        if minuti == 'e' or minuti == 'E':
            return ""
        secondi = input("Secondi:")
        if secondi == 'e' or secondi == 'E':
            return ""

        try:
            giorno = int(giorno)
            mese = int(mese)
            anno = int(anno)
            ora = int(ora)
            minuti = int(minuti)
            secondi = int(secondi)

            tmp = datetime.datetime(year=anno, month=mese, day=giorno, hour=ora, minute=minuti, second=secondi)
            correctDate = True
        except:
            correctDate = False

        if correctDate == True:
            return tmp
        else:
            print("Data non valida")


if __name__ == '__main__':

    actualLink = ""

    print("Inizializzazione in corso, attendere prego...")
    inizializza_compagnie()

    print("Benvenuto")
    while True:
        print("1 - Invia offerta last minute")
        print("2 - Invia interesse utente")
        print("e - Esci (utilizzabile in ogni menu')")
        c = input()
        if c == "1":
            while True:
                valid = ""
                for key in compagnie.keys():
                    if compagnie[key] != "":
                        valid += key + " "
                if valid != "":
                    print("Ecco le compagnie disponibili:")
                    for nome in valid.split():
                        if nome == "rayanair":
                            print("R - Rayanair")
                        elif nome == "britishaw":
                            print("B - British Airways")
                        elif nome == "japanairl":
                            print("J - Japan Airlines")
                        elif nome == "emirates":
                            print("M - Emirates")
                    while True:
                        comp = input("Inserisci la compagnia desiderata: ").upper()
                        if comp == "R" and compagnie["rayanair"] != "":
                            actualLink = compagnie["rayanair"]
                            break
                        elif comp == "B" and compagnie["britishaw"] != "":
                            actualLink = compagnie["britishaw"]
                            break
                        elif comp == "J" and compagnie["japanairl"] != "":
                            actualLink = compagnie["japanairl"]
                            break
                        elif comp == "M" and compagnie["emirates"] != "":
                            actualLink = compagnie["emirates"]
                            break
                        elif comp == "E":
                            break
                        else:
                            print("scelta non valida.")
                else:
                    print("Non sono presenti compagnie al momento")
                    break
                print("Inserimento dati volo")
                if comp == "E":
                    break

                partenza= ""
                destinazione= ""
                prezzo= 0
                offcode= ""
                data = ""



                while True:
                    print("Inserisci l'aereoporto di PARTENZA tra gli aereoporti disponibili:")
                    print(airports)
                    partenza = input().upper()
                    if partenza == "E":
                        break
                    if partenza in airports:
                        break
                    print("Aereoporto inesistente, scegli tra quelli esistenti")
                if partenza == "E":
                    break

                while True:
                    print("Inserisci l'aereoporto di DESTINAZIONE tra gli aereoporti disponibili:")
                    print(airports)
                    destinazione = input().upper();
                    if destinazione == "E":
                        break
                    if destinazione in airports:
                        if partenza != destinazione:
                            break
                        else:
                            print("L'aereoporto di partenza e quello di destinazione coincidono")
                    else:
                        print("Aereoporto inesistente, scegli tra quelli esistenti")
                if destinazione == "E":
                    break

                while True:
                    print("Inserisci il prezzo")
                    try:
                        prezzo = input()
                        if prezzo == "e" or prezzo == "E":
                            break
                        prezzo = int(prezzo)
                        if prezzo>0 and prezzo<5000:
                            break
                        else:
                            print("Il prezzo inserito non è corretto, ritente")
                    except ValueError:
                        print("inserimento non valido, ritenta")
                if prezzo == "e" or prezzo == "E":
                    break


                print("Inserimento data:")
                data = insert_data()
                if data == "":
                    break

                company= ""

                if comp == "R":
                    offcode = "rayanair" + str(random.randint(0, 500))
                    company= "rayanair"
                elif comp == "B":
                    offcode = "britishaw" + str(random.randint(0, 500))
                    company = "britishaw"
                elif comp == "J":
                    offcode = "japanairl" + str(random.randint(0, 500))
                    company = "japanairl"
                elif comp == "M":
                    offcode = "emirates" + str(random.randint(0, 500))
                    company = "emirates"

                print("Stai per inserire la seguente offerta:")
                print(partenza)
                print(destinazione)
                print(offcode)
                print(str(prezzo) + "$")
                print(data)
                while True:
                    conf=input("Vuoi confermare S/N: ").upper()
                    if (conf=="S"):
                        lmoffer = {
                            "companyname": company,
                            "flight": {
                                "departure-from": partenza,
                                "takeoff": data.strftime("%d/%m/%Y, %H:%m%p, CET"),
                                "destination": destinazione,
                                "price": {
                                    "amount": prezzo,
                                    "currency": "$"
                                },
                                "offer_code": offcode,

                            }

                        }

                        r = requests.post(compagnie[company]+"/LMflight", json=lmoffer, headers=auth_header)

                        #requests.post("LM_offers", {"lmoffer": {"value": lmoffer, "type": "String"}})
                        #simpleCamunda1RESTPost.sendMessage("LM_offers", {"lmoffer": {"value": lmoffer, "type": "String"}})
                        print("Offerta inserita")
                        break
                    elif (conf == "N" or conf == "n"):
                        print("Annullato")
                        break
                    else:
                        print("Scelta non valida")
                if conf == "S":
                    break

        #--------------------------------------------------------------

        if c == "2":

            departure_airport = ""
            arrival_airport = ""
            departure_time_min = ""
            departure_time_max = ""
            return_time_min = ""
            return_time_max = ""
            cost = 0
            username = ""
            clientAddress = ""


            while True:
                print("Come intendi generare l'interesse?")
                print("1 - Manualmente")
                print("2 - Automaticamente")
                print("e - Esci")
                c = input()
                if c == "1":
                    print("Inserimento dati interesse")
                    while True:
                        print("Inserisci l'aereoporto di PARTENZA tra gli aereoporti disponibili: ")
                        print(airports)
                        departure_airport = input().upper();
                        if departure_airport == "E":
                            break
                        if departure_airport in airports:
                            break
                        print("Aereoporto inesistente, scegli tra quelli esistenti")
                    if departure_airport == "E":
                        break

                    while True:
                        print("Inserisci l'aereoporto di DESTINAZIONE tra gli aereoporti disponibili:")
                        print(airports)
                        arrival_airport = input().upper();
                        if arrival_airport == "E":
                            break
                        if arrival_airport in airports:
                            break
                        print("Aereoporto inesistente, scegli tra quelli esistenti")
                    if arrival_airport == "E":
                        break

                    while True:
                        print("Inserisci il prezzo")
                        try:
                            cost = input()
                            if cost == "e" or cost == "E":
                                break
                            cost = int(cost)
                            if cost > 0 and cost < 5000:
                                break
                            else:
                                print("Il prezzo inserito non è corretto, ritente")
                        except ValueError:
                            print("inserimento non valido, ritenta")
                    if cost == "e" or cost == "E":
                        break

                    print("Inserimento delle fasce orarie")

                    print("Inserisci data e orario MIN per la PARTENZA")
                    min_part=insert_data()
                    if min_part == "":
                        break
                    departure_time_min= min_part.strftime("%Y/%m/%d %H:%M:%S")

                    while True:
                        print("Inserisci data e orario MAX per la PARTENZA")
                        max_part=insert_data()
                        if max_part == "":
                            break
                        if max_part >= min_part:
                            break
                        else:
                            print("Il MIN supera il MAX, inserisci nuovamente i due estremi.")
                    if max_part == "":
                        break
                    departure_time_max=max_part.strftime("%Y/%m/%d %H:%M:%S")

                    while True:
                        ret = input("Inserire anche il ritorno? S/N: ").upper()
                        if(ret == "N"):
                            break
                        elif(ret == "S"):
                            print("Inserisci data e orario MIN per il RITORNO")
                            while True:
                                min_ret = insert_data()
                                if min_ret == "":
                                    break
                                if min_ret >= min_part:
                                    break
                                else:
                                    print(
                                        "La data del RITORNO è precedente alla data di ANDATA. Inseriscine una corretta.")
                            if min_ret == "":
                                break
                            return_time_min = min_ret.strftime("%Y/%m/%d %H:%M:%S")

                            print("Inserisci data e orario MAX il RITORNO")
                            while True:
                                max_ret = insert_data()
                                if max_ret == "":
                                    break
                                if max_ret >= min_part:
                                    break;
                                else:
                                    print("Il MIN supera il MAX, inserisci nuovamente i due estremi.")
                            if max_ret == "":
                                break
                            return_time_max = max_ret.strftime("%Y/%m/%d %H:%M:%S")
                            break
                        else:
                            print("Scelta non valida. Riprova")

                    while True:
                        username = input("Inserisci l'username (senza ','): ")
                        if "," not in username:
                            break
                    if username == "e" or username == "E":
                        break

                    clientAddress = input("Inserisci l'indirizzo dell'abitazione dell'utente: ")
                    if clientAddress == "e" or clientAddress == "E":
                        break



                    #qui i campi sono tutti separati
                    print("Andrai ad inserire i seguenti valori:")
                    print(departure_airport)
                    print(arrival_airport)
                    print(departure_time_min + " - " + departure_time_max)
                    print(return_time_min + " - " + return_time_max)
                    print(str(cost))
                    print(username)
                    print(clientAddress)

                    while True:
                        conf = input("Vuoi confermare S/N: ").upper()
                        if (conf == "S"):
                            simpleCamundaRESTPost.sendMessage("StartClientProcess",
                                                              {
                                        "customInterest": {"value": True, "type": "Boolean"},
                                        "first_airport" : {"value" : departure_airport, "type": "String"},
                                        "second_airport" : {"value" : arrival_airport, "type": "String"},
                                        "firstDate" : {"value" : departure_time_min, "type": "String"},
                                        "firstDateInterval" : {"value" : departure_time_max, "type": "String"},
                                        #se il ritorno è opzionale mettere la stringa vuota!!
                                        "secondDate" : {"value" : return_time_min, "type": "String"},
                                        "secondDateInterval" : {"value" : return_time_max, "type": "String"},
                                        "username" : {"value" : username, "type": "String"},
                                        "clientAddress": {"value": clientAddress, "type": "String"},
                                        "max_price" : {"value" : str(cost), "type": "Integer"}
                                  })
                            print("Interesse creato")
                            break
                        elif (conf == "N" or conf == "n"):
                            print("Annullato")
                            break
                        else:
                            print("Scelta non valida")

                if c=="2":
                    simpleCamundaRESTPost.sendMessage("StartClientProcess")
                    print("Interesse creato")
                if c =="e":
                    break
        if c == "e" or c == "E":
            quit()

#off last minute
#creare interessi o a mano o a caqso
#se int manuale chiedere se vuole una lista degli utenti gia usati
#se mette un nome usr già usato warn (chedere se cambiarlo
