FROM adoptopenjdk:11-jre-hotspot
COPY /target/producer-app.jar /app/producer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/producer.jar"]