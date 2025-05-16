FROM openjdk:23-jdk-slim

# Setting the working directory inside of the container
WORKDIR /app

# Copying the JAR file into the container
COPY target/identity_service-0.0.1-SNAPSHOT.jar identity_service.jar

EXPOSE 8081

# Commands to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "identity_service.jar"]
