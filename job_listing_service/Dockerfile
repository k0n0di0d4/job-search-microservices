FROM openjdk:23-jdk-slim

# Setting the working directory inside of the container
WORKDIR /app

# Copying the JAR file into the container
COPY target/job_listing_service-0.0.1-SNAPSHOT.jar job_listing_service.jar

EXPOSE 8082

# Commands to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "job_listing_service.jar"]
