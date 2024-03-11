FROM openjdk:8-jre-alpine
COPY target/*.jar saldoBancario.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "saldoBancario.jar"]
