FROM openjdk:17-jdk-slim
ADD target/eurikaServer-1.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]