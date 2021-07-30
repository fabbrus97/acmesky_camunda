# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model_ import Model
from openapi_server import util


class OfferMessage(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, code=None, description=None):  # noqa: E501
        """OfferMessage - a model defined in OpenAPI

        :param code: The code of this OfferMessage.  # noqa: E501
        :type code: str
        :param description: The description of this OfferMessage.  # noqa: E501
        :type description: str
        """
        self.openapi_types = {
            'code': str,
            'description': str
        }

        self.attribute_map = {
            'code': 'code',
            'description': 'description'
        }

        self._code = code
        self._description = description

    @classmethod
    def from_dict(cls, dikt) -> 'OfferMessage':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The offerMessage of this OfferMessage.  # noqa: E501
        :rtype: OfferMessage
        """
        return util.deserialize_model(dikt, cls)

    @property
    def code(self):
        """Gets the code of this OfferMessage.

        Codice identificativo della coppia cliente destinatario - offerta  # noqa: E501

        :return: The code of this OfferMessage.
        :rtype: str
        """
        return self._code

    @code.setter
    def code(self, code):
        """Sets the code of this OfferMessage.

        Codice identificativo della coppia cliente destinatario - offerta  # noqa: E501

        :param code: The code of this OfferMessage.
        :type code: str
        """

        self._code = code

    @property
    def description(self):
        """Gets the description of this OfferMessage.

        Breve descrizione dell'offerta  # noqa: E501

        :return: The description of this OfferMessage.
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description):
        """Sets the description of this OfferMessage.

        Breve descrizione dell'offerta  # noqa: E501

        :param description: The description of this OfferMessage.
        :type description: str
        """

        self._description = description
