FROM openjdk:17-jdk-slim
COPY /target/to-mixer-application.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
