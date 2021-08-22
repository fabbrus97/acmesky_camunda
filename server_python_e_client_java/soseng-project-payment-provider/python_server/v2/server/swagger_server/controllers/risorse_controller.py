import connexion
import six

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.link_body import LinkBody  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server.models.paymenta_data_body import PaymentaDataBody  # noqa: E501
from swagger_server import util


def get_link(body=None):  # noqa: E501
    """Genera link di pagamento

    È la risorsa che, a fronte di una richiesta HTTP nella cui intestazione vi è un &#x60;token&#x60; identificativo valido, restituisce un link di pagamento relativo all&#x27;offerta specificata. # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: InlineResponse200
    """
    if connexion.request.is_json:
        body = LinkBody.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def post_paymentdata(body=None):  # noqa: E501
    """Ricevi dati di pagamento

    È la risorsa che permette al cliente intenzionato ad acquistare un&#x27;offerta di inviare al fornitore dei servizi bancari il pagamento corrispondente. # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: None
    """
    if connexion.request.is_json:
        body = PaymentaDataBody.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def post_registration(body=None):  # noqa: E501
    """Registra un nuovo utente

    È la risorsa che permette a chi intende interagire con il fornitore dei servizi bancari di ottenere l&#x27;APIKey per poter essere identificato e autorizzato. # noqa: E501

    :param body: 
    :type body: dict | bytes

    :rtype: InlineResponse2001
    """
    if connexion.request.is_json:
        body = MapsV1Credentials.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'
