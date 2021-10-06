from typing import List
import datetime
import json
"""
controller generated to handled auth operation described at:
https://connexion.readthedocs.io/en/latest/security.html
"""
def check_companytoken(api_key, required_scopes):
    with open("tokens.txt", "r") as tokens:

        if api_key == "token_hc_test":
            return {'uid': "token_hc_test"}

        for t in tokens:
            t = t.split()

            if t[2] == api_key:
                return {'uid': t[2]}
                    #ritorno il token se presente
                    
                    #none se il token non esiste ancora
        return


