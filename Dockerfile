FROM maven:3.6.3-adoptopenjdk-11

LABEL authors="Giulia"

WORKDIR /app

RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "package", "clean", "--fail-never"]

COPY . /app

FROM openjdk:11

COPY target/HangmanASD-1.0-SNAPSHOT.jar /app/target/HangmanASD-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/target/HangmanASD-1.0-SNAPSHOT.jar"]