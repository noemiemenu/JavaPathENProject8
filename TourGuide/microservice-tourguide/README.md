# Microservice rewards

## Build the container

```bash
docker build --build-arg JAR_FILE=build/libs/microservice-tourguide-0.0.1-SNAPSHOT.jar -t tourguide/microservice-tourguide .
```

## Run the container

```bash
docker run -d -p 8181:8181 --network host --name tourguideMicroserviceTourguide tourguide/microservice-tourguide
```