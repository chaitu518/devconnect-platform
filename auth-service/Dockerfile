# Step 1: Use OpenJDK image
FROM eclipse-temurin:17-jdk

# Step 2: Set workdir
WORKDIR /app

# Step 3: Copy JAR into container
COPY build/libs/auth-service.jar app.jar

# Step 4: Expose port
EXPOSE 8081

# Step 5: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
