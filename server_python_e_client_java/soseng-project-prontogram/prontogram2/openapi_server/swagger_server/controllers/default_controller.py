import connexion
import six

from swagger_server.models.body import Body  # noqa: E501
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
        body = Body.from_dict(connexion.request.get_json())  # noqa: E501
        with open("users.json", "w") as users_file:
                users = [] 
                try:
                    users = json.load(users_file)
                    for u in users:
                        if u["user"] == body.username:
                            return None , 400
                        
                except:
                    return None , 500
                
                if len(body.password) < 5:
                    #La password deve avere almeno 6 caratteri
                    return None, 400
                users.append({"username": body.username, "password": body.password})
                json.dump(users, users_file)
                users_file.close()
    return None, 200

