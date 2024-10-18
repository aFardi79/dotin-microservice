FROM openjdk:17-jdk-slim
ADD target/gatewayservice-1.0-SNAPSHOT.jar app.jar
EXPOSE 6060
ENTRYPOINT ["java","-jar","app.jar"]