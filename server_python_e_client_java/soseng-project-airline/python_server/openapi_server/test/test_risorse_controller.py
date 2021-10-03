# coding: utf-8

from __future__ import absolute_import
import unittest

from flask import json
from six import BytesIO

from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.models.lmflight import Lmflight  # noqa: E501
from openapi_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from openapi_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_flights(self):
        """Test case for get_flights

        Restituisci le offerte attive
        """
        headers = { 
            'Accept': 'application/json',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/flights',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_lmflight(self):
        """Test case for post_lmflight

        crea un volo last minute
        """
        lmflight = openapi_server.Lmflight()
        headers = { 
            'Content-Type': 'application/json',
            'companytoken': 'special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/LMflight',
            method='POST',
            headers=headers,
            data=json.dumps(lmflight),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_notifypayment(self):
        """Test case for post_notifypayment

        Ricevi pagamento
        """
        inline_object = openapi_server.InlineObject()
        headers = { 
            'Content-Type': 'application/json',
            'companytoken': 'special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/notifypayment',
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
        maps_v1_credentials = {"username":"Mario","password":"12a4a54s"}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/registration',
            method='POST',
            headers=headers,
            data=json.dumps(maps_v1_credentials),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
