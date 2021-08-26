FROM openjdk:11-jdk-slim

ARG JAR_FILE=./build/libs/microservice-rewards-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} microservice-rewards.jar

EXPOSE 8383

ENTRYPOINT ["java", "-jar", "/microservice-rewards.jar"]