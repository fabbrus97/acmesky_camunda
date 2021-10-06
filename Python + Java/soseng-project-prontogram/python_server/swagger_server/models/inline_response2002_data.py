# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.offer_message import OfferMessage  # noqa: F401,E501
from swagger_server import util


class InlineResponse2002Data(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, offer: OfferMessage=None, _date: str=None, id: int=None):  # noqa: E501
        """InlineResponse2002Data - a model defined in Swagger

        :param offer: The offer of this InlineResponse2002Data.  # noqa: E501
        :type offer: OfferMessage
        :param _date: The _date of this InlineResponse2002Data.  # noqa: E501
        :type _date: str
        :param id: The id of this InlineResponse2002Data.  # noqa: E501
        :type id: int
        """
        self.swagger_types = {
            'offer': OfferMessage,
            '_date': str,
            'id': int
        }

        self.attribute_map = {
            'offer': 'offer',
            '_date': 'date',
            'id': 'id'
        }
        self._offer = offer
        self.__date = _date
        self._id = id

    @classmethod
    def from_dict(cls, dikt) -> 'InlineResponse2002Data':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The inline_response_200_2_data of this InlineResponse2002Data.  # noqa: E501
        :rtype: InlineResponse2002Data
        """
        return util.deserialize_model(dikt, cls)

    @property
    def offer(self) -> OfferMessage:
        """Gets the offer of this InlineResponse2002Data.


        :return: The offer of this InlineResponse2002Data.
        :rtype: OfferMessage
        """
        return self._offer

    @offer.setter
    def offer(self, offer: OfferMessage):
        """Sets the offer of this InlineResponse2002Data.


        :param offer: The offer of this InlineResponse2002Data.
        :type offer: OfferMessage
        """

        self._offer = offer

    @property
    def _date(self) -> str:
        """Gets the _date of this InlineResponse2002Data.


        :return: The _date of this InlineResponse2002Data.
        :rtype: str
        """
        return self.__date

    @_date.setter
    def _date(self, _date: str):
        """Sets the _date of this InlineResponse2002Data.


        :param _date: The _date of this InlineResponse2002Data.
        :type _date: str
        """

        self.__date = _date

    @property
    def id(self) -> int:
        """Gets the id of this InlineResponse2002Data.


        :return: The id of this InlineResponse2002Data.
        :rtype: int
        """
        return self._id

    @id.setter
    def id(self, id: int):
        """Sets the id of this InlineResponse2002Data.


        :param id: The id of this InlineResponse2002Data.
        :type id: int
        """

        self._id = id
