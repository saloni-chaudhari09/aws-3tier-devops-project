FROM amazoncorretto:21

WORKDIR /app

COPY target/devops-java-app-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java", "-cp", "app.jar", "com.devops.App"]
