# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.register_body import RegisterBody  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_post_register(self):
        """Test case for post_register

        Registra un nuovo utente
        """
        body = RegisterBody()
        response = self.client.open(
            '/mocks/soseng-unibo/soseng-project-documentation/6636887/register',
            method='POST',
            data=json.dumps(body),
            content_type='application/vnd.api+json')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
