FROM openjdk:11 as is-mutant-service

MAINTAINER Caique Fialho "dev.caiquemacedof@gmail.com"

COPY target/api-is-mutant-0.0.1-SNAPSHOT-spring-boot.jar api-is-mutant.jar

ENV DATABASE_HOST=docker-mysql
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=root

EXPOSE 8080

ENTRYPOINT ["java","-DDATABASE_HOST=docker-mysql -DDATABASE_USERNAME=root -DDATABASE_PASSWORD=root","-jar","/api-is-mutant.jar"]