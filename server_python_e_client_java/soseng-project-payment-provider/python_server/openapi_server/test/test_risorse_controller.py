# coding: utf-8

from __future__ import absolute_import
import unittest

from flask import json
from six import BytesIO

from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_object1 import InlineObject1  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from openapi_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_link(self):
        """Test case for get_link

        Genera link di pagamento
        """
        inline_object1 = {}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'authorization_api_key': 'authorization_api_key_example',
            'apikey': 'special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/link',
            method='GET',
            headers=headers,
            data=json.dumps(inline_object1),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_paymentdata(self):
        """Test case for post_paymentdata

        Ricevi dati di pagamento
        """
        inline_object = {}
        headers = { 
            'Content-Type': 'application/json',
            'authorization_api_key': 'authorization_api_key_example',
            'apikey': 'special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/paymentdata',
            method='POST',
            headers=headers,
            data=json.dumps(inline_object),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_registration(self):
        """Test case for post_registration

        Registra un nuovo utente
        """
        maps_v1_credentials = {"username":"Mario","password":"1245a5a"}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/registration',
            method='POST',
            headers=headers,
            data=json.dumps(maps_v1_credentials),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
