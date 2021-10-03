import connexion
import six

from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_object1 import InlineObject1  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from openapi_server import util

import string 
import random
import datetime
import requests
import json

import simpleCamundaRESTPost

active_payment_links = []
token_comp_aerea = ""

compagnie = {
        "rayanair": "",
        "britishaw": "",
        "japanairl": "",
        "emirates": ""
    }

def inizializza_compagnie():
    f = open("airline.list", "r")
    print("inizializzo compagnie...")
    for x in f:
        print("Sto esaminando l'url", x.replace("\n", "")+"/flights")
        r = requests.get(x.replace("\n", "")+"/flights")

        print(r.text)

        if "rayanair" in r.text:
            print("Trovato ryanair")
            compagnie["rayanair"] = x
        if "britishaw" in r.text:
            compagnie["britishaw"] = x
        if "japanairl" in r.text:
            compagnie["japanairl"] = x
        if "emirates" in r.text:
            print("Trovato emirates")
            compagnie["emirates"] = x

def get_link(inline_object1=None):  # noqa: E501
    """Genera link di pagamento

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce un link di pagamento relativo all&#39;offerta specificata. # noqa: E501

    :param authorization_api_key: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione.
    :type authorization_api_key: str
    :param inline_object1: 
    :type inline_object1: dict | bytes

    :rtype: InlineResponse200
    """
    print("sono in get_link")
    if connexion.request.is_json:
        inline_object1 = InlineObject1.from_dict(connexion.request.get_json())  # noqa: E501
        if inline_object1.amount.value <= 0:
            print("Errore con la quantità di danari")
            return "Error in amount value", 400
        if inline_object1.amount.currency not in ["eur", "usd"]:
            print("Errore con la currency")
            return "Currency non found", 400
    chars = '1234567890'
    link = ""
    print("tutto ok, genero il link")
    for _ in range(0, 10):
        link += chars[random.randint(0, len(chars)-1)] 
    print("generato link ", link)
    payment = {"link": link, "data": inline_object1.to_dict()}
    active_payment_links.append(payment)
    ret = InlineResponse200(link=link) #{"link": link}

    return ret

def pay_company(payment_data_link, payment_data): 

    inizializza_compagnie()

    '''
    Il pagamento è andato bene, diamo una percentuale 
    ad acmesky e una alla compagnia aerea 
    '''
    

    #notifica pagamento compagnia aerea

    url = compagnie[payment_data_link["data"]["airline"]].replace("\n", "")
    
    print("url airline: ", url)

    global token_comp_aerea 

    if len(token_comp_aerea) == 0:
        r = requests.post(url+"/registration", json={"username": "serv_bancari", "password": "12345abcde"})
        print("r: ", r)
        token_comp_aerea = (json.loads(r.text))["token"] #TODO non sono sicuro sia così
    notifica_comp_aerea = {
        "offer_code": payment_data_link['data']['offer_code'],
        "customer": {
            "name": "", #TODO
            "email": ""
        }, 
        "amount_payed": {
            "value": payment_data_link['data']['amount']['value'],
            "currency": payment_data_link['data']['amount']['currency']
        },
        "transaction": {
            "date": str(datetime.date.today()),
            "id": int(payment_data_link['link'])
        }    
    }
    headers = {"abcd12!": token_comp_aerea} #TODO
    requests.post(url+"/notifypayment", json=notifica_comp_aerea, headers=headers)
    

def post_paymentdata(inline_object=None):  # noqa: E501
    """Ricevi dati di pagamento

    È la risorsa che permette al cliente intenzionato ad acquistare un&#39;offerta di inviare al fornitore dei servizi bancari il pagamento corrispondente. # noqa: E501

    :param authorization_api_key: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione.
    :type authorization_api_key: str
    :param inline_object: 
    :type inline_object: dict | bytes

    :rtype: None
    """

    if connexion.request.is_json:
        inline_object = InlineObject.from_dict(connexion.request.get_json())  # noqa: E501
    for p in active_payment_links:
        print("controllo il link: ", inline_object.transaction.id)
        if inline_object.transaction.id == p["link"]:
            print("trovata corrispondenza!!!")
            if inline_object.card_number > 100000: #la carta ha almeno 6 cifre
                #check expiration
                today = datetime.date.today()
                year = today.year
                month = today.month
                if year <= inline_object.expiration.year and month < inline_object.expiration.month:
                    if inline_object.circuit in ["visa", "mastercard"]:
                        simpleCamundaRESTPost.sendMessage("ClientPaymentConfirmed", {"paymCorrect": {"value": True, "type": "Boolean"}}) #conferma pagamento per cliente - successo
                        simpleCamundaRESTPost.sendMessage("ConfirmPaymentSuccessfull", {"paymLink": {"value": inline_object.transaction.id, "type": "String"}, "paymSucc": {"value": True, "type": "Boolean"}}) #acmesky
                        pay_company(p, inline_object)
                        active_payment_links.remove(p)
                        return #successo
    simpleCamundaRESTPost.sendMessage("ConfirmPaymentSuccessfull", {"paymLink": {"value": inline_object.transaction.id, "type": "String"}, "paymSucc": {"value": False, "type": "Boolean"}}) #conferma pagamento per acmesky - fallimento
    simpleCamundaRESTPost.sendMessage("ClientPaymentConfirmed", {"paymCorrect": {"value": False, "type": "Boolean"}}) #conferma pagamento per cliente - fallimento
    
    return 'Error in payment data', 400


def post_registration(maps_v1_credentials=None):  # noqa: E501
    """Registra un nuovo utente

    È la risorsa che permette a chi intende interagire con il fornitore dei servizi bancari di ottenere l&#39;APIKey per poter essere identificato e autorizzato. # noqa: E501

    :param maps_v1_credentials: 
    :type maps_v1_credentials: dict | bytes

    :rtype: InlineResponse2001
    """
    if connexion.request.is_json:
        maps_v1_credentials = MapsV1Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    if len(maps_v1_credentials.username) > 0 and len(maps_v1_credentials.password) > 0: #TODO si potrebbe controllare che username/pwd siano univoci
        chars = string.ascii_letters + '1234567890'
        token = ""
        for _ in range(0, 20):
            token += chars[random.randint(0, len(chars)-1)]

        tokens = []
        with open("tokens.json") as tokens_file:
            try:
                tokens = json.load(tokens_file)
            except:
                pass
            tokens.append({"user_id": maps_v1_credentials.username, "token": token})
            
        tokens_file.close()
        with open("tokens.json", "w") as tokens_file:
            json.dump(tokens, tokens_file)
            tokens_file.close()
        
        ret = InlineResponse2001(token=token) #{"token": token}
        return ret
    else:
        return "Bad username or password", 400
