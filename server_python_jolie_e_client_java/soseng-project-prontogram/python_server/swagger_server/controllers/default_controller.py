import connexion
import six

from swagger_server.models.register_body import RegisterBody  # noqa: E501
from swagger_server import util

import json

def post_register(body=None):  # noqa: E501
    """Registra un nuovo utente

    Registra un nuovo utente; prende in input solo un nome utente e una password # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        print("eseguo register")
        body = RegisterBody.from_dict(connexion.request.get_json())  # noqa: E501
        with open("users.json", "r+") as users_file:
                users = [] 
                try:
                    print("carico file degli utenti")
                    users = json.load(users_file)
                    print("File degli utenti caricato")
                    for u in users:
                        if u["username"] == body.username:
                            print("Utente già registrato")
                            return None , 400
                        
                except Exception as e:
                    print(e)
                    return None , 500
                if len(body.password) < 5:
                    #La password deve avere almeno 6 caratteri
                    return None, 400
                users.append({"username": body.username, "password": body.password})
                users_file.seek(0)
                users_file.truncate(0)
                json.dump(users, users_file)
                users_file.close()
    return None, 200

