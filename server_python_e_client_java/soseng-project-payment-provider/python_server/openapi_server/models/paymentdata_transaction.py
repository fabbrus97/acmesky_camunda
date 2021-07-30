# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model_ import Model
from openapi_server import util


class PaymentdataTransaction(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, id=None, date=None):  # noqa: E501
        """PaymentdataTransaction - a model defined in OpenAPI

        :param id: The id of this PaymentdataTransaction.  # noqa: E501
        :type id: str
        :param date: The date of this PaymentdataTransaction.  # noqa: E501
        :type date: str
        """
        self.openapi_types = {
            'id': str,
            'date': str
        }

        self.attribute_map = {
            'id': 'id',
            'date': 'date'
        }

        self._id = id
        self._date = date

    @classmethod
    def from_dict(cls, dikt) -> 'PaymentdataTransaction':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The _paymentdata_transaction of this PaymentdataTransaction.  # noqa: E501
        :rtype: PaymentdataTransaction
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self):
        """Gets the id of this PaymentdataTransaction.


        :return: The id of this PaymentdataTransaction.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this PaymentdataTransaction.


        :param id: The id of this PaymentdataTransaction.
        :type id: str
        """

        self._id = id

    @property
    def date(self):
        """Gets the date of this PaymentdataTransaction.


        :return: The date of this PaymentdataTransaction.
        :rtype: str
        """
        return self._date

    @date.setter
    def date(self, date):
        """Sets the date of this PaymentdataTransaction.


        :param date: The date of this PaymentdataTransaction.
        :type date: str
        """

        self._date = date
