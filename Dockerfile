# syntax=docker/dockerfile:1

FROM eclipse-temurin:11-jre-focal
WORKDIR /app
COPY ./build/libs/mower-1.0.0.jar .
CMD ["java", "-jar", "mower-1.0.0.jar"]
