# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model_ import Model
from openapi_server.models.offer_message import OfferMessage
from openapi_server import util

from openapi_server.models.offer_message import OfferMessage  # noqa: E501

class InlineResponse200Message(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, offer=None, receiver=None, date=None, id=None):  # noqa: E501
        """InlineResponse200Message - a model defined in OpenAPI

        :param offer: The offer of this InlineResponse200Message.  # noqa: E501
        :type offer: OfferMessage
        :param receiver: The receiver of this InlineResponse200Message.  # noqa: E501
        :type receiver: str
        :param date: The date of this InlineResponse200Message.  # noqa: E501
        :type date: str
        :param id: The id of this InlineResponse200Message.  # noqa: E501
        :type id: int
        """
        self.openapi_types = {
            'offer': OfferMessage,
            'receiver': str,
            'date': str,
            'id': int
        }

        self.attribute_map = {
            'offer': 'offer',
            'receiver': 'receiver',
            'date': 'date',
            'id': 'id'
        }

        self._offer = offer
        self._receiver = receiver
        self._date = date
        self._id = id

    @classmethod
    def from_dict(cls, dikt) -> 'InlineResponse200Message':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The inline_response_200_message of this InlineResponse200Message.  # noqa: E501
        :rtype: InlineResponse200Message
        """
        return util.deserialize_model(dikt, cls)

    @property
    def offer(self):
        """Gets the offer of this InlineResponse200Message.


        :return: The offer of this InlineResponse200Message.
        :rtype: OfferMessage
        """
        return self._offer

    @offer.setter
    def offer(self, offer):
        """Sets the offer of this InlineResponse200Message.


        :param offer: The offer of this InlineResponse200Message.
        :type offer: OfferMessage
        """

        self._offer = offer

    @property
    def receiver(self):
        """Gets the receiver of this InlineResponse200Message.


        :return: The receiver of this InlineResponse200Message.
        :rtype: str
        """
        return self._receiver

    @receiver.setter
    def receiver(self, receiver):
        """Sets the receiver of this InlineResponse200Message.


        :param receiver: The receiver of this InlineResponse200Message.
        :type receiver: str
        """

        self._receiver = receiver

    @property
    def date(self):
        """Gets the date of this InlineResponse200Message.


        :return: The date of this InlineResponse200Message.
        :rtype: str
        """
        return self._date

    @date.setter
    def date(self, date):
        """Sets the date of this InlineResponse200Message.


        :param date: The date of this InlineResponse200Message.
        :type date: str
        """

        self._date = date

    @property
    def id(self):
        """Gets the id of this InlineResponse200Message.


        :return: The id of this InlineResponse200Message.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this InlineResponse200Message.


        :param id: The id of this InlineResponse200Message.
        :type id: int
        """

        self._id = id
