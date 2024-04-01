#immagine di base da cui costruire l'immagine docker
FROM maven:3.6.3-adoptopenjdk-21

LABEL authors="Giulia"

#tutti i comandi successivi verranno eseguiti all'interno di questa directory
WORKDIR /app

#esegue Maven per compilare il progetto Java all'interno del container Docker
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "package", "clean"]

#copio il progetto locale in app
COPY . /app

FROM openjdk:21

COPY target/HangmanASD-1.0-SNAPSHOT.jar /app/target/HangmanASD-1.0-SNAPSHOT.jar

EXPOSE 8080

#esegue il programma jar
CMD ["java", "-jar", "/app/target/HangmanASD-1.0-SNAPSHOT.jar"]