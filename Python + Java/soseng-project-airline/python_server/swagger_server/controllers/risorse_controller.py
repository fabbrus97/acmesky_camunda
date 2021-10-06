import connexion
import six

import random
import string
import datetime
import json

from swagger_server.models.notifypayment_body import NotifypaymentBody  # noqa: E501
from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501

from swagger_server.models.l_mflight_flight_price import LMflightFlightPrice
from swagger_server.models.l_mflight_flight import LMflightFlight

from swagger_server.models.lmflight import Lmflight

from swagger_server import util
from random import randint, seed

import simpleCamundaRESTPost

def add_flight():
    sources = ["airport BLQ",
               "airport BGY",
               "airport CTA",
               "airport MXP",
               "airport VRN",
               "airport FCO",
               "airport LGW",
               "airport FRA",
               "airport BCN",
               "airport LIS",
               "airport AUH",
               "airport SVO", 
               "airport ORY"]

    now = datetime.datetime.now()

    filew = open("voli.txt", "a")

    src = sources[random.randint(0, len(sources))]
    dst = sources[random.randint(0,len(sources))]
    while src == dst:
        dst = sources[random.randint(0,len(sources))]

    now +=datetime.timedelta(weeks=random.randint(0, 5),days=random.randint(0, 20),hours=random.randint(0,24),minutes=random.randint(0, 60),seconds=random.randint(0, 5))
    #aggiungo un delta alla data
    price = random.randint(20, 1400)

    company = ""

    if price < 60:
        company = "rayanair"
    elif price < 500:
        company = "britishaw"
    elif price < 1000:
        company = "japanairl"
    else:
        company = "emirates"

    offcode = company + str(random.randint(0,500))

    filew.write(src +" " +dst +" " +now.strftime("%d/%m/%Y %H:%M %p") +" " +str(price)+ " " + company + " " + offcode + "\n")


def get_flights():  # noqa: E501
    """Restituisci le offerte attive

    È la risorsa che restituisce tutte le offerte di voli attive. # noqa: E501
    :rtype: InlineResponse200
    """

    now = datetime.datetime.now()
    company=""

    with open("config.txt", "r") as c:
        fl = c.readline()
        fl=fl.split(":")
        company=fl[1].strip()
    
    with open("voli.txt", "r") as f:
        lines = f.readlines()

    deleted = 0
    with open("voli.txt", "w") as f:
        for line in lines:
            volo = line.split()
            data_volo = volo[2]+" "+volo[3]+" "+volo[4]
            date_time_obj = datetime.datetime.strptime(data_volo, '%d/%m/%Y %H:%M %p')
            if date_time_obj > now:
                f.write(line)
            else:
                deleted+=1
            #riscrivo solo se la data è ancora valida, gli altri verranno cancellati
            
    while deleted > 0:
        add_flight()
        deleted -=1
        #aggiungo tanti voli quanti ne sono stati eliminati

    with open("voli.txt", "r") as f:
            lines = f.readlines()
            #ricarico la lista corretta
    ray=[]
    brit=[]
    jpn=[]
    emi=[]

    for line in lines:
        r=line.split()
        if r[6] == "rayanair":
            ray.append(line)
        elif r[6] == "britishaw":
            brit.append(line)
        elif r[6] == "japanairl":
            jpn.append(line)
        else:
            emi.append(line)
    
    selected = []

    if company == "rayanair":
            selected = ray
    elif company == "britishaw":
            selected = brit
    elif company == "japanairl":
            selected = jpn
    elif company == "emirates":
            selected = emi
    else:
            print("not valid")
            quit()
            #se il parametro nel file è scritto in modo errato
    
    flights=[]
    for line in selected:
        l=line.split()
        price = LMflightFlightPrice(amount=l[5],currency="€")
        tkoff = l[2]+", "+ l[3]+l[4]+", CET"
        flight= LMflightFlight(departure_from=l[0],takeoff=tkoff,destination=l[1],price=price,offer_code=l[7])
        flights.append(flight)
    
    return InlineResponse200(flights=flights,companyname=company)


def post_lmflight(lmflight=None):  # noqa: E501
    """crea un volo last minute
     #converti json a stringa
     # noqa: E501

    :param lmflight: 
    :type lmflight: dict | bytes

    :rtype: None
    """

    if connexion.request.is_json:

        lmflight = Lmflight.from_dict(connexion.request.get_json())

        """
        f = {"flight": {
    "departure-from": "BLQ",
    "takeoff": "10/10/2021",
    "destination": "BGY",
    "price": {
        "amount": 123,
        "currency": "$"
    },
    "offer_code": "abcdefg"
}, "companyname": "testcompany"
}"""

        print("Devo inviare l'offerta last minute: ")
        print(lmflight.to_str())

        lmjson= lmflight.to_str().replace("\'", "\"")
        lmf = json.loads(lmjson)

        filew = open("voli.txt", "a")
        
        takeoff =  lmf["flight"]["takeoff"]
        takeoff = datetime.datetime.strptime(takeoff, "%d/%m/%Y, %H:%M%p, CET")
        takeoff = takeoff.strftime("%d/%m/%Y %H:%M %p")

        filew.write(lmf["flight"]["departure_from"] + " " + lmf["flight"]["destination"] + " " + takeoff + " " + str(int(lmf["flight"]["price"]["amount"])) + " " + lmf["companyname"] + " " + lmf["flight"]["offer_code"] + "\n")
        filew.close()

        try:
            simpleCamundaRESTPost.sendMessage("LM_offers", {"lmflights": {"value": json.dumps(lmf), "type": "String"}})
            return 201
        except:
            return "Error", 400
    #return "empty body", 400

def post_notifypayment(inline_object=None):  # noqa: E501
    """Ricevi pagamento

    È la risorsa che permette al fornitore dei servizi bancari di inviare alla compagnia aerea la quota del pagamento ricevuto dal cliente per l&#39;acquisto dell&#39;offerta. # noqa: E501

    :param inline_object: 
    :type inline_object: dict | bytes

    :rtype: None
    """
    print("INVIO NOTIFICA PAGAMENTO")

    if connexion.request.is_json:
        inline_object = NotifypaymentBody.from_dict(connexion.request.get_json())  # noqa: E501
        print(inline_object)
        simpleCamundaRESTPost.sendMessage("getTicket", variables={
		"username": {"value": inline_object.customer.name, "type": "String"}, 
                "transactionid": {"value": str(inline_object.transaction.id), "type": "String"}, 
		"offer_code": {"value": inline_object.offer_code, "type": "String"} })
        '''
        esempio: simplecamundarestpost.sendMessage("LM_Offer", {	
		    "aVariable" : {"value" : "aNewValue", "type": "String"},
		    "anotherVariable" : {"value" : true, "type": "Boolean"}	
	        })
        '''
    return "notify payment ok", 200

    #invio del biglietto aereo al cliente



def post_registration(maps_v1_credentials=None):  # noqa: E501
    """Registra un nuovo utente

    È la risorsa che permette a chi intende interagire con la compagnia aerea di ottenere l&#39;APIKey per poter essere identificato e autorizzato. # noqa: E501

    :param maps_v1_credentials: 
    :type maps_v1_credentials: dict | bytes

    :rtype: InlineResponse2001
    """
    if connexion.request.is_json:
        maps_v1_credentials = MapsV1Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    
    if len(maps_v1_credentials.username) > 0 and len(maps_v1_credentials.password) > 0:

        usr = maps_v1_credentials.username
        pw = maps_v1_credentials.password

        resp = ""
        exists = 0

        def id_generator(size=19, chars=string.ascii_uppercase + string.digits):
            return ''.join(random.choice(chars) for _ in range(size))

        with open("tokens.txt", "r") as f:
            users = f.readlines()

        with open("tokens.txt", "w") as us:

            for user in users:
                u = user.split()

                if u[0] == usr:
                    if u[1]== pw:
                        us.write(user)
                        resp =  InlineResponse2001(token=u[2])
                        exists = 1
                    else:
                        us.write(user)
                        resp = "Username already taken", 400
                        exists = 1
                else:
                    us.write(user)
       
            if(exists == 0):
                token = id_generator()
                us.write(usr + " " + pw +" " + token +"\n")
                resp = InlineResponse2001(token=token)

    return resp
