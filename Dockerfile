FROM gcr.io/distroless/java17
COPY target/calculator-1.0.jar /app/app.jar
CMD ["app.jar","--server.port=8081"]