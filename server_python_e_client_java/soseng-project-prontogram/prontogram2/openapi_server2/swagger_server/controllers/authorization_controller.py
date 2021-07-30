from typing import List
"""
controller generated to handled auth operation described at:
https://connexion.readthedocs.io/en/latest/security.html
"""

import json, datetime

def check_basicAuth(username, password, required_scopes):
    with open("users.json") as user_file:
        users = json.load(user_file)
        for u in users:
            if username == u["username"]:
                if password == u["password"]:
                    print("login successful for", username)
                    return {"user": username}
    #return {'test_key': 'test_value'}
    print("login failed")
    return None

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
                    token.remove(token)
                    return

    return
    #return {'test_key': 'test_value'}


