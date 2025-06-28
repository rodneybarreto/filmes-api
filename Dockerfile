FROM openjdk:17-alpine
LABEL br.com.rodneybarreto="rodneybarreto"
WORKDIR /opt/movies-api
COPY target/movies-api-0.0.1-SNAPSHOT.jar movies-api.jar
ENTRYPOINT ["java","-jar","movies-api.jar"]