# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class InlineResponse2003(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, token: str=None, expiration_date: str=None):  # noqa: E501
        """InlineResponse2003 - a model defined in Swagger

        :param token: The token of this InlineResponse2003.  # noqa: E501
        :type token: str
        :param expiration_date: The expiration_date of this InlineResponse2003.  # noqa: E501
        :type expiration_date: str
        """
        self.swagger_types = {
            'token': str,
            'expiration_date': str
        }

        self.attribute_map = {
            'token': 'token',
            'expiration_date': 'expiration_date'
        }
        self._token = token
        self._expiration_date = expiration_date

    @classmethod
    def from_dict(cls, dikt) -> 'InlineResponse2003':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The inline_response_200_3 of this InlineResponse2003.  # noqa: E501
        :rtype: InlineResponse2003
        """
        return util.deserialize_model(dikt, cls)

    @property
    def token(self) -> str:
        """Gets the token of this InlineResponse2003.


        :return: The token of this InlineResponse2003.
        :rtype: str
        """
        return self._token

    @token.setter
    def token(self, token: str):
        """Sets the token of this InlineResponse2003.


        :param token: The token of this InlineResponse2003.
        :type token: str
        """

        self._token = token

    @property
    def expiration_date(self) -> str:
        """Gets the expiration_date of this InlineResponse2003.


        :return: The expiration_date of this InlineResponse2003.
        :rtype: str
        """
        return self._expiration_date

    @expiration_date.setter
    def expiration_date(self, expiration_date: str):
        """Sets the expiration_date of this InlineResponse2003.


        :param expiration_date: The expiration_date of this InlineResponse2003.
        :type expiration_date: str
        """

        self._expiration_date = expiration_date
