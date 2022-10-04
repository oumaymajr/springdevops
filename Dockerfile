FROM openjdk:8-jdk-alpine

WORKDIR /spring-app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
