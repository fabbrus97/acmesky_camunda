import connexion
import six

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.lmflight import Lmflight  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server.models.notifypayment_body import NotifypaymentBody  # noqa: E501
from swagger_server import util


def get_flights():  # noqa: E501
    """Restituisci le offerte attive

    È la risorsa che restituisce tutte le offerte di voli attive. # noqa: E501


    :rtype: InlineResponse200
    """
    return 'do some magic!'


def post_lmflight(body=None):  # noqa: E501
    """crea un volo last minute

     # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = Lmflight.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def post_notifypayment(body=None):  # noqa: E501
    """Ricevi pagamento

    È la risorsa che permette al fornitore dei servizi bancari di inviare alla compagnia aerea la quota del pagamento ricevuto dal cliente per l&#x27;acquisto dell&#x27;offerta. # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = NotifypaymentBody.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def post_registration(body=None):  # noqa: E501
    """Registra un nuovo utente

    È la risorsa che permette a chi intende interagire con la compagnia aerea di ottenere l&#x27;APIKey per poter essere identificato e autorizzato. # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: InlineResponse2001
    """
    if connexion.request.is_json:
        body = MapsV1Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
