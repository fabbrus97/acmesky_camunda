from typing import List
"""
controller generated to handled auth operation described at:
https://connexion.readthedocs.io/en/latest/security.html
"""

import json, datetime

def check_tokenSessione(token):

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
    
    #return {'test_key': 'test_value'}


