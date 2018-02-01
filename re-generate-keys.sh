#!/bin/bash

openssl genrsa -out signing.pem 4096
openssl rsa -in signing.pem -pubout > signing_pub.pem

openssl genrsa -out encryption.pem 4096
openssl rsa -in encryption.pem -pubout > encryption_pub.pem

mv -f signing.pem encryption_pub.pem jwt-generator-webapp/src/main/resources
mv -f encryption.pem signing_pub.pem jwt-verifier-webapp/src/main/resources
