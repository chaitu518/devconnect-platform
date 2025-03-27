# Use a base image with Java 17
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your service runs on
EXPOSE 8082

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
