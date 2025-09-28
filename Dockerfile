# Stage 1: Build
FROM maven:3.9.3-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy only necessary files to leverage caching
COPY pom.xml .
COPY src ./src

# Run tests and build the JAR
RUN mvn -B clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/calculator-1.0.jar app.jar

# Expose port
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]
