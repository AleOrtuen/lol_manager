# Fase di build
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Fase di runtime
FROM openjdk:17-jdk-alpine

# Aggiungi la versione del Cloud SQL Proxy
# RUN wget https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -O /usr/local/bin/cloud_sql_proxy \
#     && chmod +x /usr/local/bin/cloud_sql_proxy

# Etichetta per gli autori
LABEL org.opencontainers.image.authors="Alessio Cappelletto"

# Copia il JAR del progetto nel container
# COPY --from=build /app/target/lol_manager-0.0.1-SNAPSHOT.jar /lol_manager-0.0.1-SNAPSHOT.jar

COPY --from=build /app/target/lol_manager-0.0.1-SNAPSHOT.jar /app/lol_manager.jar
ENTRYPOINT ["java", "-jar", "/app/lol_manager.jar"]

