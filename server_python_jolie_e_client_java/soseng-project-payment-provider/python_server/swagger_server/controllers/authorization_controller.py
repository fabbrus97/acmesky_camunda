from typing import List
import json
"""
controller generated to handled auth operation described at:
https://connexion.readthedocs.io/en/latest/security.html
"""
def check_apikey(api_key, required_scopes):
    print("tentativo di login!")
    with open("tokens.json") as tokens_file:
        tokens = json.load(tokens_file)
        for token in tokens:
            if token["token"] == api_key:
                return {'uid': 0}
                #NOTA: uid dev'essere un numero, non può essere una stringa tipo "acmesky"
                # ma siccome sarà solo acmesky a usare questi metodi, non ha molto senso 
                # implementare degli uid perché avrà sempre lo stesso valore - quello corrispondente
                # ad acmesky
    print("tentativo fallito")
    return


