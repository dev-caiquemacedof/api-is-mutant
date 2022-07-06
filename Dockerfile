FROM openjdk:11 as is-mutant-service

COPY target/api-is-mutant-0.0.1-SNAPSHOT-spring-boot.jar api-is-mutant.jar

EXPOSE 8080

ENTRYPOINT ["java","-DDATABASE_HOST=docker-mysql","-jar","/api-is-mutant.jar"]