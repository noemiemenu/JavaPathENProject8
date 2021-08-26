# Microservice rewards

## Build the container

```bash
docker build --build-arg JAR_FILE=build/libs/microservice-rewards-0.0.1-SNAPSHOT.jar -t tourguide/microservice-rewards .
```

## Run the container

```bash
docker run -d -p 8383:8383 --name tourguideMicroserviceRewards tourguide/microservice-rewards
```