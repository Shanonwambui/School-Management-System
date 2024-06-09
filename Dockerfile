
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw package --no-daemon


FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build target/sms3-0.0.1-SNAPSHOT.jar sms-app.jar

ENTRYPOINT ["java", "-jar", "sms-app.jar"]