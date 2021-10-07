# coding: utf-8

from __future__ import absolute_import
import unittest

from flask import json
from six import BytesIO

from openapi_server.models.credentials import Credentials  # noqa: E501
from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_post_distance(self):
        """Test case for post_distance

        Calcola distanza geografica
        """
        inline_object = {}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Basic Zm9vOmJhcg==',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/2282674/distance',
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
        credentials = {"username":"Mario","password":"Albatros12"}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/2282674/registration',
            method='POST',
            headers=headers,
            data=json.dumps(credentials),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
