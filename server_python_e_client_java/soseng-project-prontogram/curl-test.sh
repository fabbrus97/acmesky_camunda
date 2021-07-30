#!/bin/bash

URL="http://localhost:8080/mocks/soseng-unibo/soseng-project-documentation/6636887"

case "${1}" in
    login) curl -X POST $URL"/login" \
		--header "Content-Type: application/json" \
	        --data "{\"username\": \"brus\", \"password\": \"1234abcd\"}" ;;
    send) curl -X POST $URL"/createmessage" \
	       --header "Content-Type: application/vnd.api+json" \
	       --header "Authorization: Bearer $2" \
	       --data   " {\"data\": {\"receiver\": \"mariorossi\", \"offer\": {\"code\": \"codicemagnifico\", \"description\": \"descrizione magnifica\"}}}" ;;
    msgid) curl $URL"/message/brus/$3" \
           --header "Authorization: Bearer $2" ;;
    all) curl $URL"/message" \
           --header "Authorization: Bearer $2" ;;
    allfrom) curl $URL"/message/brus/from/$3" \
           --header "Authorization: Bearer $2" ;;
esac
