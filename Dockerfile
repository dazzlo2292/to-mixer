FROM eclipse-temurin:17-jdk
COPY /target/to-mixer-application.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
