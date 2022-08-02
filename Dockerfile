FROM openjdk:8-jdk-alpine
MAINTAINER Hiten Pratap Singh<hitenpratap99@gmail.com>
WORKDIR /
COPY build/libs/finbite-deve-test-1.0-SNAPSHOT.jar incoice-total.jar
ENTRYPOINT ["java","-jar","/incoice-total.jar"]