from typing import List
import datetime
import json


def info_from_companytoken(api_key, required_scopes):
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
    with open("tokens.txt", "r") as tokens:
        for t in tokens:
            t = t.split()

            if t[2] == api_key:
                return {'uid': t[2]}
                    #ritorno il token se presente
                    
                    #none se il token non esiste ancora
        return


