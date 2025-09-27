# Stage 1: Build the JAR
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR (including tests)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the built JAR
COPY --from=build /app/target/calculator-1.0.jar app.jar

# Expose port 8081 for the app
EXPOSE 8081

# Run the Spring Boot app on port 8081
ENTRYPOINT ["java","-jar","app.jar","--server.port=8081"]
