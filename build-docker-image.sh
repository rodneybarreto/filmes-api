#!/usr/bin/env sh

## Generate docker image
docker build . -t rodneybarreto/filmes-api:0.0.1

## Push to docker hub
#docker push rodneybarreto/filmes-api:0.0.1

## Run container
#docker run --name filmes-api --network=host -p 8081:8081 rodneybarreto/filmes-api:0.0.1

## Salvar imagem para importar em outra VM #############################################################################
#cd /tmp
#docker images
#docker save -o filmes-api.tar rodneybarreto/filmes-api:0.0.1
#scp filmes-api.tar rodney@192.168.122.45:/tmp

## Na outra VM
#cd /tmp
#docker load -i filmes-api.tar
#docker images
#docker tag 15fa69157785 rodneybarreto/filmes-api:0.0.1
########################################################################################################################