# Microservice rewards

## Build the container

```bash
docker build --build-arg JAR_FILE=build/libs/microservice-users-0.0.1-SNAPSHOT.jar -t tourguide/microservice-users .
```

## Run the container

```bash
docker run -d -p 8282:8282 --name tourguideMicroserviceUsers tourguide/microservice-users
```