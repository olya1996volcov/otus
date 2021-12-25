FROM adoptopenjdk:11-jre-hotspot
COPY /target/consumer-app.jar /app/consumer.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/consumer.jar"]