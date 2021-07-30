#!/bin/bash

case "${1}" in
    link) curl -X POST "http://localhost:8080/mocks/soseng-unibo/soseng-project-documentation/7424070/link" \
    -H "accept: application/json" -H  "Content-Type: application/json" \
    -H "abcd12!: iRt17FThi0RcMHo6PbOJ" -H "AuthorizationAPIKey: iRt17FThi0RcMHo6PbOJ" \
    -d "{\"airline\":\"string\",\"offer_code\":\"string\", \"amount\": {\"value\": 100, \"currency\": \"eur\"}}" ;;
    registration) curl -X POST "http://localhost:8080/mocks/soseng-unibo/soseng-project-documentation/7424070/registration" -H "accept: application/json" -H  "Content-Type: application/json" -d "{\"password\":\"1245a5a\",\"username\":\"Mario\"}" ;;
    pay)  curl -X POST "http://localhost:8080/mocks/soseng-unibo/soseng-project-documentation/7424070/paymentdata" -H  "accept: */*" -H  "AuthorizationAPIKey: sfausfkgfk" -H  "Content-Type: application/json" -H "abcd12!: akjsdfgasg" \
    -d "{\"CVC\":0,\"card_number\":0,\"circuit\":\"string\",\"expiration\":{\"month\":10,\"year\":2030},\"transaction\":{\"id\":\"1234\",\"date\":\"2021-03-27\"}}" ;;

esac
