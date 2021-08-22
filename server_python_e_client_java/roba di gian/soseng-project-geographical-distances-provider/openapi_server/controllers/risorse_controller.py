import connexion
import six

import googlemaps
from datetime import datetime

import random
import string

from openapi_server.models.credentials import Credentials  # noqa: E501
from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.models.inline_response200_distance import InlineResponse200Distance
from openapi_server import util


def post_distance(inline_object=None):  # noqa: E501
    """Calcola distanza geografica

    È la risorsa che restituisce la distanza geografica tra due punti geografici oppure le distanze geografiche tra un punto e ciascun punto di una lista. # noqa: E501

    :param inline_object: 
    :type inline_object: dict | bytes

    :rtype: InlineResponse200
    """
    if connexion.request.is_json:
        inline_object = InlineObject.from_dict(connexion.request.get_json())  # noqa: E501
    
    gmaps = googlemaps.Client(key='AIzaSyBZUO21297_6NX990v2Rv14-UILuoIuaKg')

    start = inline_object.point_a
    dest = inline_object.points_b

    #x= '{"distance": ['
    distances = []
    for d in dest:
        distance_result = gmaps.distance_matrix(start, d, mode="driving", departure_time="now", units="metric")
        distance= distance_result["rows"][0]["elements"][0]["distance"]["text"].split()
        #x= x + '{"value":' + distance[0] +', "unit": "'+ distance[1] +'"},'
        distances.append(InlineResponse200Distance(value=distance[0],unit=distance[1]))

    #x = x[:-1]
    #x= x + '] }'

    return InlineResponse200(distance=distances)


def post_registration(credentials=None):  # noqa: E501
    """Registra un nuovo utente

    È la risorsa che permette ad un utente di registrarsi, dopo aver fornito la propria username e password. # noqa: E501

    :param credentials: 
    :type credentials: dict | bytes

    :rtype: InlineResponse2001
    """
    if connexion.request.is_json:
        credentials = Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    
    if len(credentials.username) > 0 and len(credentials.password) > 0:

        usr = credentials.username
        pw = credentials.password

        resp = ""
        exists = 0

        def id_generator(size=19, chars=string.ascii_uppercase + string.digits):
            return ''.join(random.choice(chars) for _ in range(size))

        with open("users.txt", "r") as f:
            users = f.readlines()

        with open("users.txt", "w") as us:

            for user in users:
                u = user.split()

                if u[0] == usr:
                    if u[1]== pw:
                        us.write(user)
                        resp =  InlineResponse2001(token=u[2])
                        exists = 1
                    else:
                        us.write(user)
                        resp = "Username already taken", 400
                        exists = 1
                else:
                    us.write(user)
       
            if(exists == 0):
                token = id_generator()
                us.write(usr + " " + pw +" " + token +"\n")
                resp = InlineResponse2001(token=token)

    return resp
