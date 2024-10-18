FROM openjdk:17-jdk-slim
ADD target/notification-1.0-SNAPSHOT.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","app.jar"]