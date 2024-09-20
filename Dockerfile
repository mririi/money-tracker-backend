# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
# Build the application using the production profile
RUN mvn -f pom.xml clean package -DskipTests -Pprod

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
# Run the application with the prod profile
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]