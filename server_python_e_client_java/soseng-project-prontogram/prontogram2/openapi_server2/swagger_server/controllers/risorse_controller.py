import connexion
import six

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response200_links import InlineResponse200Links  # noqa: E501
from swagger_server.models.inline_response200_data import InlineResponse200Data  # noqa: E501
from swagger_server.models.inline_response200_message import InlineResponse200Message  # noqa: E501
from swagger_server.models.inline_response200_links1 import InlineResponse200Links1  # noqa: E501
from swagger_server.models.inline_response2002 import InlineResponse2002  # noqa: E501
from swagger_server.models.inline_response2003 import InlineResponse2003  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server.models.message import Message  # noqa: E501


from swagger_server.models.inline_response2002_data import InlineResponse2002Data
from swagger_server.models.inline_response2002_links import InlineResponse2002Links


from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.inline_response2002 import InlineResponse2002  # noqa: E501
from swagger_server.models.inline_response2003 import InlineResponse2003  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server import util

import string 
import random
import datetime
import json

import simpleCamundaRESTPost

db_message = []
db_registered_users = []

def get_username_from_uid(uid):
    with open("tokens.json") as tokens_file:
        tokens = json.load(tokens_file)
        for token in tokens:
            if token["uid"] == uid:
                tokens_file.close()
                return token["user"]
    tokens_file.close()

def get_message_username(username, token_info):  # noqa: E501
    """Restituisci i primi 10 messaggi del cliente

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce i primi 20 messaggi inviati al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  In caso la richiesta HTTP abbia esito positivo, la risposta è un reindirizzamento alla risorsa &#x60;/message/{username}/from/0&#x60; # noqa: E501

    :param username: Username dell&#39;utente
    :type username: str
    :param authorization_oauth2: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione, preceduto da &#x60;Bearer&#x60;
    :type authorization_oauth2: str

    :rtype: None
    """

    user = get_username_from_uid(token_info["uid"])

    return 'Redirect', 308, {'Location': "/message/{}/from/0".format(username)}

def get_message_username_from_messageid(username, messageid, token_info=None):  # noqa: E501
    """Restituisci 10 messaggi del cliente

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce i 10 messaggi successivi a quello con id pari al parametro di query &#x60;messageId&#x60; e inviati al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  Il body della richiesta e della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#39;API prontogramAPI è al livello 3 di modello di maturità di Richardson: nella risposta sono presenti anche i campi &#x60;links&#x60; per permettere l&#39;esplorazione dinamica, come richiesto da RMML3. # noqa: E501

    :param username: Username dell&#39;utente
    :type username: str
    :param messageid: Id del messaggio
    :type messageid: str
    :param authorization_oauth2: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione, preceduto da &#x60;Bearer&#x60;
    :type authorization_oauth2: str

    :rtype: InlineResponse2001
    """

    messages = []
    more_than_10 = False
    user = get_username_from_uid(token_info["uid"])
    if user == username:
        for message in db_message:
            if (message["from"] == username or message["receiver"] == username) and (message["data"]["id_snd"] >= int(messageid) or message["data"]["id_rcv"] >= int(messageid)):
                if message["from"] == user:
                    links = InlineResponse200Links1(message["link"]["self_snd"],message["link"]["next_snd"],message["link"]["prev_snd"] )
                    data = InlineResponse200Message(message["data"]["offer"], message["receiver"], message["data"]["date"], message["data"]["id_rcv"])
                    msg = InlineResponse200Data(data, links)
                    messages.append(msg)
                if message["receiver"] == user:
                    links = InlineResponse200Links1(message["link"]["self_rcv"],message["link"]["next_rcv"],message["link"]["prev_rcv"] )
                    data = InlineResponse200Message(message["data"]["offer"], message["receiver"], message["data"]["date"], message["data"]["id_rcv"])
                    msg = InlineResponse200Data(data, links)
                    messages.append(msg)
                
                
                if len(messages) == 11:
                    more_than_10 = True
                    messages.pop()
                    break

    prev_messageid = 0
    
    if (int(messageid) > 10):
        prev_messageid = int(messageid) - 10

    base_links = {
            "self": "/message/{}/from/{}".format(user, messageid),
            "prev": "/message/{}/from/{}".format(user, prev_messageid),
            "next": "/message/{}/from/{}".format(user, messageid)

    }
    if more_than_10:
        base_links["next"] = "/message/{}/from/{int(messageid) + 10}".format(user)
    
    links = InlineResponse200Links(base_links["self"], base_links["next"], base_links["prev"])  

    return InlineResponse200(data = InlineResponse200Data(messages), links= links)

def get_message_username_messageid(username, messageid, token_info = None):  # noqa: E501
    """Restituisci il messaggio del cliente

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce il messaggio con id pari al parametro di query &#x60;messageid&#x60; inviato al cliente il cui username è pari al parametro di query &#x60;username&#x60;.  Il body della richiesta e della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#39;API prontogramAPI è al livello 3 di modello di maturità di Richardson: nella risposta sono presenti anche i campi &#x60;links&#x60; per permettere l&#39;esplorazione dinamica, come richiesto da RMML3. # noqa: E501

    :param username: Username dell&#39;utente
    :type username: str
    :param messageid: Id del messaggio
    :type messageid: str
    :param authorization_oauth2: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione, preceduto da &#x60;Bearer&#x60;
    :type authorization_oauth2: str

    :rtype: InlineResponse2002
    """
    user = get_username_from_uid(token_info["uid"])

    print("{} richiede l'accesso al messagio {}".format(user, messageid))

    if user == username:
        for message in db_message:
            if message["from"] == username:
                if message["data"]["id_snd"] == int(messageid):
                    data  = InlineResponse2002Data(message["data"]["offer"], message["data"]["date"], message["data"]["id_snd"])
                    links = InlineResponse2002Links(message["link"]["self_snd"], message["link"]["next_snd"], message["link"]["prev_snd"])
                    return InlineResponse2002(data, links)
                if  message["data"]["id_rcv"] == int(messageid):
                    data  = InlineResponse2002Data(message["data"]["offer"], message["data"]["date"], message["data"]["id_rcv"])
                    links = InlineResponse2002Links(message["link"]["self_rcv"], message["link"]["next_rcv"], message["link"]["prev_rcv"])
                    return InlineResponse2002(data, links)

def post_allmessage(token_info=None):  # noqa: E501
    """Restituisci tutti i messaggi

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce tutti i messaggi (in blocchi di cardinalità 20) accessibili al fruitore (utente o ACMESky) identificato da quel token: in dettaglio, l&#39;utente ha accesso solo ai messaggi di cui è destinatario, mentre ACMESky ha accesso a tutti i messaggi che ha inoltrato ai suoi utenti mediante Prontogram (potrebbe richiederli per fini statistici, ad esempio).  l body della risposta HTTP è di tipo &#x60;application/vnd.api+json&#x60; in quanto l&#39;API prontogramAPI è al livello 3 di modello di maturità di Richardson: con la risposta sono restituiti anche i campi &#x60;links&#x60; per permettere l&#39;esplorazione dinamica, come richiesto da RMML3. # noqa: E501

    :param authorization_oauth2: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione, preceduto da &#x60;Bearer&#x60;
    :type authorization_oauth2: str

    :rtype: InlineResponse200
    """
    messages = []
    user = get_username_from_uid(token_info["uid"])
    more_than_10 = False
    for message in db_message:
        if message["from"] == user:
            links = InlineResponse200Links1(message["link"]["self_snd"],message["link"]["next_snd"],message["link"]["prev_snd"] )
            data = InlineResponse200Message(message["data"]["offer"], message["receiver"], message["data"]["date"], message["data"]["id_rcv"])
            msg = InlineResponse200Data(data, links)
            messages.append(msg)
        if message["receiver"] == user:
            links = InlineResponse200Links1(message["link"]["self_rcv"],message["link"]["next_rcv"],message["link"]["prev_rcv"] )
            data = InlineResponse200Message(message["data"]["offer"], message["receiver"], message["data"]["date"], message["data"]["id_rcv"])
            msg = InlineResponse200Data(data, links)
            messages.append(msg)

        if len(messages) > 10:
            more_than_10 = True
            messages.pop()
            break
    
    base_links = {
            "self": "/message/{}/from/0".format(user),
            "prev": "/message/{}/from/0".format(user),
            "next": "/message/{}/from/0".format(user)
    }
    if more_than_10:
        base_links["next"] = "/message/{}/from/10".format(user)
    
    links = InlineResponse200Links(base_links["self"], base_links["next"], base_links["prev"])  

    return InlineResponse200(data = InlineResponse200Data(messages), links= links)

def post_createmessage(token_info=None, body=None):  # noqa: E501
    """Invia messaggio

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, invia un messaggio il cui corpo e destinatario sono quelli specificati nel body della richiesta HTTP. # noqa: E501

    :param authorization_oauth2: Il valore deve essere il token che il cliente ha ricevuto durante la registrazione, preceduto da &#x60;Bearer&#x60;
    :type authorization_oauth2: str
    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = body.from_dict(connexion.request.get_json()).to_dict()  # noqa: E501

    id_snd = len(db_message) #ATTENZIONE! 
    # siccome tutti i messaggi sono inviati solo da acmesky, 
    # l'id associato ai suoi messaggi è semplicemente
    # il numero dei messaggi inviati dal'alba dei tempi
    prev_snd = id_snd
    if id_snd > 0:
        prev_snd -= 1

    id_rcv = 0
    for m in db_message:
        if m["receiver"] == body["data"]["receiver"]:
            id_rcv += 1
        if m["link"]["self_rcv"] == m["link"]["next_rcv"]:
                m["link"]["next_rcv"] = "/" + m["link"]["next_rcv"].split("/")[1] + "/" + str(int(m["link"]["next_rcv"].split("/")[2]) + 1)
        if m["link"]["self_snd"] == m["link"]["next_snd"]:
                m["link"]["next_snd"] = "/" + m["link"]["next_snd"].split("/")[1] + "/" + str(int(m["link"]["next_snd"].split("/")[2]) + 1)
    
    #TODO nota: è possibile mandare messaggi ad utenti che non sono ancora registrati
    prev_rcv = id_rcv
    if id_rcv > 0:
        prev_rcv -= 1

    #TODO devi aggiornare il valore next del messaggio precedente 
    
    msg = {
        "receiver": body["data"]["receiver"],
        "from": get_username_from_uid(token_info["uid"]),
        "data": {
            "offer": Message(body["data"]["offer"]["code"], body["data"]["offer"]["description"] ), 
            "date": datetime.date.today(), 
            "id_snd": id_snd, 
            "id_rcv": id_rcv
        }, 
        "link": {
            "self_snd": "/{get_username_from_uid(token_info['uid'])}/{id_snd}".format(id_snd), 
            "prev_snd": "/{get_username_from_uid(token_info['uid'])}/{prev_snd}".format(prev_snd), 
            "next_snd": "/{get_username_from_uid(token_info['uid'])}/{id_snd}".format(id_snd), 
            "self_rcv": "/{body['data']['receiver']}/{id_rcv}".format(id_rcv), 
            "prev_rcv": "/{body['data']['receiver']}/{prev_rcv}".format(prev_rcv), 
            "next_rcv": "/{body['data']['receiver']}/{id_rcv}".format(id_rcv)
        }
    }

    db_message.append(msg)     
    simpleCamundaRESTPost.sendMessage("CodeMessage", {"msgBody": msg, "type": "Body1"}) #TODO controlla che il tipo sia Body1

'''
def post_login(maps_v1_credentials=None):  # noqa: E501
    """Autentica un cliente

    È la risorsa che permette al cliente o ad ACMESky di ottenere il token temporaneo tramite cui potranno essere identificati e autorizzati da Prontogram. # noqa: E501

    :param maps_v1_credentials: 
    :type maps_v1_credentials: dict | bytes

    :rtype: InlineResponse2003
    """
    if connexion.request.is_json:
        maps_v1_credentials = MapsV1Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    if len(maps_v1_credentials.username) > 0 and len(maps_v1_credentials.password) > 0: 
        #TODO non posso fare questo controllo perché non esiste un endpoint per registrarsi

        with open("users.json", "w") as users_file:
                try:
                    db_registered_users = json.load(users_file)
                except:
                    return InlineResponse2003("Errore del server"), 500

        for user in db_registered_users:
            if maps_v1_credentials.username == user["user"] and maps_v1_credentials.password == user["password"]:
                user = maps_v1_credentials.username
                chars = string.ascii_letters + '1234567890'
                token = ""
                for _ in range(0, 20):
                    token += chars[random.randint(0, len(chars))]
                now = datetime.datetime.now() #per verificare se il tempo è scaduto, basta fare
                # (datetime.datetime.now().timestamp() - now.timestamp())/60
                # se il risultato è > 60, sono passati più di 60 minuti e il token è scaduto
                with open("tokens.json", "w") as tokens_file:
                    tokens = [] 
                    try:
                        tokens = json.load(tokens_file)
                        for t in tokens:
                            if t["user"] == maps_v1_credentials.username:
                                #l'utente esiste già, aggiorniamo questi valori
                                t["token"] = token
                                t["exp"]   = now.timestamp()
                    except:
                        pass
                    #l'utente non esiste nella lista degli utenti loggati, lo aggiungiamo
                    tokens.append({"user": user, "token": token, "exp": now.timestamp(), "uid": len(tokens)})
                    json.dump(tokens, tokens_file)
                    tokens_file.close()
                    
                            
                return InlineResponse2003(token=token) #TODO possiamo restituire anche expiration_date

    return InlineResponse2003("Username or password wrong"), 400

'''

def post_login(token_info=None):
    print("qualcuno sta facendo il login")
    if token_info:
        print("Login effettuato con successo per utente", token_info["user"])
    return