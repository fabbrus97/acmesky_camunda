from typing import List
import json

def info_from_apikey(api_key, required_scopes):
    """
    Check and retrieve authentication information from api_key.
    Returned value will be passed in 'token_info' parameter of your operation function, if there is one.
    'sub' or 'uid' will be set in 'user' parameter of your operation function, if there is one.

    :param api_key API key provided by Authorization header
    :type api_key: str
    :param required_scopes Always None. Used for other authentication method
    :type required_scopes: None
    :return: Information attached to provided api_key or None if api_key is invalid or does not allow access to called API
    :rtype: dict | None
    """
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


