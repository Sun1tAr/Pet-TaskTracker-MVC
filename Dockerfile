# Stage 1: сборка с Maven и Java 21
FROM maven:3.9.6-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: запуск с Java 21
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]