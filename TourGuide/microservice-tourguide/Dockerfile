FROM openjdk:11-jdk-slim

ARG JAR_FILE=./build/libs/microservice-tourguide-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} microservice-tourguide.jar

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "/microservice-tourguide.jar"]