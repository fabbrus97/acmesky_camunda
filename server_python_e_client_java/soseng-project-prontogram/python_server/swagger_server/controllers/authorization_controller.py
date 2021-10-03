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
                    data = {"user": username}
                    return data
    #return {'test_key': 'test_value'}
    print("login failed")
    return None

def check_tokenSessione(token):
    with open("tokens.json") as tokens_file:
        tokens = json.load(tokens_file)
        for user,t in tokens.items():
            
            if t["token"] == token :
                if (datetime.datetime.now().timestamp() - float(t["issued"]))/60 < 3600 :
                    #supponiamo i token valgano un'ora
                    print("token valido")
                    return {'uid': t["token"]}
                else :
                    print("Il token è scaduto")
                    tokens.pop(user)
                    return

    return
    #return {'test_key': 'test_value'}


