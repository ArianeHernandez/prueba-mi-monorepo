#!/bin/bash

# get auth token or grant access to the EC2 machine to access CodeArtifact
CODEARTIFACT_AUTH_TOKEN={}
# replace with the desire version (i.e 7.0.0)
VERSION={}

rm webapps/OsmoAutenticador.war

curl -L --request GET \
    https://nuvu-624097007419.d.codeartifact.us-east-1.amazonaws.com/maven/cp-suite/cc/nuvu/OsmoAutenticador/$VERSION/OsmoAutenticador-$VERSION.war \
    --user "user:$CODEARTIFACT_AUTH_TOKEN" > webapps/OsmoAutenticador.war