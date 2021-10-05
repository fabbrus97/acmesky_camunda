# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.lmflight import Lmflight  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server.models.notifypayment_body import NotifypaymentBody  # noqa: E501
from swagger_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_flights(self):
        """Test case for get_flights

        Restituisci le offerte attive
        """
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/flights',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_lmflight(self):
        """Test case for post_lmflight

        Crea un volo last minute
        """
        body = Lmflight()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/LMflight',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_notifypayment(self):
        """Test case for post_notifypayment

        Ricevi pagamento
        """
        body = NotifypaymentBody()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/notifypayment',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_registration(self):
        """Test case for post_registration

        Registra un nuovo utente
        """
        body = MapsV1Credentials()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424067/registration',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
