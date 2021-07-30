from typing import List
import json
import datetime

def info_from_tokenSessione(token):
    """
    Check and retrieve authentication information from custom bearer token.
    Returned value will be passed in 'token_info' parameter of your operation function, if there is one.
    'sub' or 'uid' will be set in 'user' parameter of your operation function, if there is one.

    :param token Token provided by Authorization header
    :type token: str
    :return: Decoded token information or None if token is invalid
    :rtype: dict | None
    """

    with open("tokens.json") as tokens_file:
        tokens = json.load(tokens_file)
        for t in tokens:
            
            if t["token"] == token :
                if (datetime.datetime.now().timestamp() - float(t["exp"]))/60 < 60 :
                    #supponiamo i token valgano un minuto
                    return {'uid': t["uid"]}
                else :
                    print("Il token è scaduto")
                    #TODO elimina token se è scaduto

    return 


