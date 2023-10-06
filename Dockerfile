FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/bot-0.0.1-SNAPSHOT.jar /app.jar
ADD conf /conf
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=/conf/application.properties"]