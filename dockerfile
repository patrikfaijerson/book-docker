FROM openjdk:11-jdk-slim-buster
COPY target/book-docker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]