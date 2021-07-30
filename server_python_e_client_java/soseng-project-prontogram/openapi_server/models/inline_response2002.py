# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from openapi_server.models.base_model_ import Model
from openapi_server.models.inline_response2002_data import InlineResponse2002Data
from openapi_server.models.inline_response2002_links import InlineResponse2002Links
from openapi_server import util

from openapi_server.models.inline_response2002_data import InlineResponse2002Data  # noqa: E501
from openapi_server.models.inline_response2002_links import InlineResponse2002Links  # noqa: E501

class InlineResponse2002(Model):
    """NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).

    Do not edit the class manually.
    """

    def __init__(self, data=None, links=None):  # noqa: E501
        """InlineResponse2002 - a model defined in OpenAPI

        :param data: The data of this InlineResponse2002.  # noqa: E501
        :type data: InlineResponse2002Data
        :param links: The links of this InlineResponse2002.  # noqa: E501
        :type links: InlineResponse2002Links
        """
        self.openapi_types = {
            'data': InlineResponse2002Data,
            'links': InlineResponse2002Links
        }

        self.attribute_map = {
            'data': 'data',
            'links': 'links'
        }

        self._data = data
        self._links = links

    @classmethod
    def from_dict(cls, dikt) -> 'InlineResponse2002':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The inline_response_200_2 of this InlineResponse2002.  # noqa: E501
        :rtype: InlineResponse2002
        """
        return util.deserialize_model(dikt, cls)

    @property
    def data(self):
        """Gets the data of this InlineResponse2002.


        :return: The data of this InlineResponse2002.
        :rtype: InlineResponse2002Data
        """
        return self._data

    @data.setter
    def data(self, data):
        """Sets the data of this InlineResponse2002.


        :param data: The data of this InlineResponse2002.
        :type data: InlineResponse2002Data
        """

        self._data = data

    @property
    def links(self):
        """Gets the links of this InlineResponse2002.


        :return: The links of this InlineResponse2002.
        :rtype: InlineResponse2002Links
        """
        return self._links

    @links.setter
    def links(self, links):
        """Sets the links of this InlineResponse2002.


        :param links: The links of this InlineResponse2002.
        :type links: InlineResponse2002Links
        """

        self._links = links
