# Immagine con Java 17
FROM eclipse-temurin:17

# Cartella di lavoro nel container
WORKDIR /app

# Copia il file JAR (compilato)
COPY target/*.jar app.jar

# Avvia Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]