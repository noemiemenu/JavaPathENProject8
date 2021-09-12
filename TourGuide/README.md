# TourGuide

## Overview:

TourGuide is a Spring Boot application. It allows users to discover attractions near of their location and provides them discounts on hotel stays and reductions on ticket prices for shows. Application is composed of three micro-services : microservice-tourGuide, microservice-rewards and microservice-users.

## Prerequisite to run it
- Java 15.0.2
- Gradle 7.1.1
- Docker

## Build

```bash
./gradlew -q build 
```

## Docker

### Start

```bash
docker-compose up -d
```

### Stop

```bash
docker-compose stop
```

