# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.inline_response2002 import InlineResponse2002  # noqa: E501
from swagger_server.models.inline_response2003 import InlineResponse2003  # noqa: E501
from swagger_server.models.message import Message  # noqa: E501
from swagger_server.models.message_list import MessageList  # noqa: E501
from swagger_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_message_username(self):
        """Test case for get_message_username

        Restituisci i primi 10 messaggi del cliente
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}'.format(username='username_example'),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_message_username_from_messageid(self):
        """Test case for get_message_username_from_messageid

        Restituisci 10 messaggi del cliente
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}/from/{messageid}'.format(username='username_example', messageid='messageid_example'),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_get_message_username_messageid(self):
        """Test case for get_message_username_messageid

        Restituisci il messaggio del cliente
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message/{username}/{messageid}'.format(username='username_example', messageid='messageid_example'),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_allmessage(self):
        """Test case for post_allmessage

        Restituisci tutti i messaggi
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/message',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_createmessage(self):
        """Test case for post_createmessage

        Invia messaggio
        """
        body = Message()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/createmessage',
            method='POST',
            data=json.dumps(body),
            content_type='application/vnd.api+json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_createmessages(self):
        """Test case for post_createmessages

        Invia messaggi
        """
        body = MessageList()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/createmessages',
            method='POST',
            data=json.dumps(body),
            content_type='application/vnd.api+json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_login(self):
        """Test case for post_login

        Autentica un cliente
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/login',
            method='POST')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
