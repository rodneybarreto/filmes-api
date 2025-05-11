FROM openjdk:17-alpine
LABEL br.com.rodneybarreto="rodneybarreto"
WORKDIR /opt/filmes-api
COPY target/filmes-api-0.0.1-SNAPSHOT.jar filmes-api.jar
ENTRYPOINT ["java","-jar","filmes-api.jar"]