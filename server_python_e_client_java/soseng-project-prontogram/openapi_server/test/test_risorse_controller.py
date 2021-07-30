# coding: utf-8

from __future__ import absolute_import
import unittest

from flask import json
from six import BytesIO

from openapi_server.models.inline_object import InlineObject  # noqa: E501
from openapi_server.models.inline_response200 import InlineResponse200  # noqa: E501
from openapi_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from openapi_server.models.inline_response2002 import InlineResponse2002  # noqa: E501
from openapi_server.models.inline_response2003 import InlineResponse2003  # noqa: E501
from openapi_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from openapi_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_message_username(self):
        """Test case for get_message_username

        Restituisci i primi 10 messaggi del cliente
        """
        headers = { 
            'authorization_oauth2': 'authorization_oauth2_example',
            'Authorization': 'Bearer special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}'.format(username='username_example'),
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_message_username_from_messageid(self):
        """Test case for get_message_username_from_messageid

        Restituisci 10 messaggi del cliente
        """
        headers = { 
            'Accept': 'application/vnd.api+json',
            'authorization_oauth2': 'authorization_oauth2_example',
            'Authorization': 'Bearer special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}/from/{messageid}'.format(username='username_example', messageid='messageid_example'),
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_message_username_messageid(self):
        """Test case for get_message_username_messageid

        Restituisci il messaggio del cliente
        """
        headers = { 
            'Accept': 'application/vnd.api+json',
            'authorization_oauth2': 'authorization_oauth2_example',
            'Authorization': 'Bearer special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}/{messageid}'.format(username='username_example', messageid='messageid_example'),
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_allmessage(self):
        """Test case for post_allmessage

        Restituisci tutti i messaggi
        """
        headers = { 
            'Accept': 'application/vnd.api+json',
            'authorization_oauth2': 'authorization_oauth2_example',
            'Authorization': 'Bearer special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_createmessage(self):
        """Test case for post_createmessage

        Invia messaggio
        """
        inline_object = {}
        headers = { 
            'Content-Type': 'application/vnd.api+json',
            'authorization_oauth2': 'authorization_oauth2_example',
            'Authorization': 'Bearer special-key',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/createmessage',
            method='POST',
            headers=headers,
            data=json.dumps(inline_object),
            content_type='application/vnd.api+json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_login(self):
        """Test case for post_login

        Autentica un cliente
        """
        maps_v1_credentials = {"username":"Mario","password":"st1222a2aring"}
        headers = { 
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/login',
            method='POST',
            headers=headers,
            data=json.dumps(maps_v1_credentials),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
