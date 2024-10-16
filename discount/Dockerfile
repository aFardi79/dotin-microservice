FROM openjdk:17-jdk-slim
ADD target/discount-1.0-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]
