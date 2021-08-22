from typing import List


def info_from_authorization(username, password, required_scopes):
    """
    Check and retrieve authentication information from basic auth.
    Returned value will be passed in 'token_info' parameter of your operation function, if there is one.
    'sub' or 'uid' will be set in 'user' parameter of your operation function, if there is one.

    :param username login provided by Authorization header
    :type username: str
    :param password password provided by Authorization header
    :type password: str
    :param required_scopes Always None. Used for other authentication method
    :type required_scopes: None
    :return: Information attached to user or None if credentials are invalid or does not allow access to called API
    :rtype: dict | None
    """
    with open("users.txt", "r") as users:
        for u in users:
            u = u.split()

            if u[0] == username:
                if u[1] == password:
                    return {'uid': u[2]}
                    #il token è già stato creato
                
            #username inesistente
        return


