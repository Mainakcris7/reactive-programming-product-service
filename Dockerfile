# Build stage
FROM eclipse-temurin:21 AS build
WORKDIR /app
COPY src src
COPY gradle gradle
COPY build.gradle .
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle .
RUN chmod +x gradlew
RUN ./gradlew clean bootJar -x test

# Execute stage
FROM eclipse-temurin:21.0.7_6-jdk-ubi9-minimal
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

