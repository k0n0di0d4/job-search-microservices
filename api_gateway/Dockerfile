# Lightweight OpenJDK image
FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/api_gateway-0.0.1-SNAPSHOT.jar api_gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api_gateway.jar"]

