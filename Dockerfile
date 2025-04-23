# Fase di build
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Fase di runtime
FROM openjdk:17-jdk-alpine

# Aggiungi la versione del Cloud SQL Proxy
RUN wget https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -O /usr/local/bin/cloud_sql_proxy \
    && chmod +x /usr/local/bin/cloud_sql_proxy

# Etichetta per gli autori
LABEL org.opencontainers.image.authors="Alessio Cappelletto"

# Copia il JAR del progetto nel container
COPY --from=build /app/target/lol_manager-0.0.1-SNAPSHOT.jar /lol_manager-0.0.1-SNAPSHOT.jar

# Avvia il Cloud SQL Proxy e poi il tuo applicativo
ENTRYPOINT ["sh", "-c", "cloud_sql_proxy -dir=/cloudsql -credential_file=/etc/credentials.json & java -jar /lol_manager-0.0.1-SNAPSHOT.jar"]
