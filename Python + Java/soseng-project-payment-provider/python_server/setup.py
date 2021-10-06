# coding: utf-8

import sys
from setuptools import setup, find_packages

NAME = "swagger_server"
VERSION = "1.0.0"
# To install the library, run the following
#
# python setup.py install
#
# prerequisite: setuptools
# http://pypi.python.org/pypi/setuptools

REQUIRES = ["connexion"]

setup(
    name=NAME,
    version=VERSION,
    description="serviziBancariAPI",
    author_email="",
    url="",
    keywords=["Swagger", "serviziBancariAPI"],
    install_requires=REQUIRES,
    packages=find_packages(),
    package_data={'': ['swagger/swagger.yaml']},
    include_package_data=True,
    entry_points={
        'console_scripts': ['swagger_server=swagger_server.__main__:main']},
    long_description="""\
    È l&#x27;API Restful offerta dal *fornitore dei servizi bancari* che rende accessibile la capability di generazione di un codice di pagamento con certi parametri e di ricevere dati relativi al pagamento di un cliente.
    """
)
