FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
LABEL org.opencontainers.image.authors="Alessio Cappelletto"
COPY --from=build /app/target/lol_manager-0.0.1-SNAPSHOT.jar lol_manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/lol_manager-0.0.1-SNAPSHOT.jar"]
