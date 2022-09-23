FROM openjdk:17.0.2
COPY target/bootstrap-1.0-jar-with-dependencies.jar app.jar
ENTRYPOINT java -jar app.jar