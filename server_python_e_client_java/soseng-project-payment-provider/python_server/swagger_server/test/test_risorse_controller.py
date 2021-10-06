# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.inline_response200 import InlineResponse200  # noqa: E501
from swagger_server.models.inline_response2001 import InlineResponse2001  # noqa: E501
from swagger_server.models.link_body import LinkBody  # noqa: E501
from swagger_server.models.maps_v1_credentials import MapsV1Credentials  # noqa: E501
from swagger_server.models.paymenta_data_body import PaymentaDataBody  # noqa: E501
from swagger_server.test import BaseTestCase


class TestRisorseController(BaseTestCase):
    """RisorseController integration test stubs"""

    def test_get_link(self):
        """Test case for get_link

        Genera link di pagamento
        """
        body = LinkBody()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/link',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_post_paymentdata(self):
        """Test case for post_paymentdata

        Ricevi dati di pagamento
        """
        body = PaymentaDataBody()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/paymentdata',
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
            '/mocks/soseng-unibo/soseng-project-documentation/7424070/registration',
            method='POST',
            data=json.dumps(body),
            content_type='application/json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
