FROM openjdk:21-slim

WORKDIR /app

COPY target/calculator-1.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar","--server.port=8081"]