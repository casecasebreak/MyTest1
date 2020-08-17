FROM openjdk:8-jre
MAINTAINER xxx xxx@lenovo.com

COPY target/app.jar /user-docker-service.jar

ENTRYPOINT ["java", "-jar", "/user-docker-service.jar"]
