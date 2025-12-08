FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/api-0.0.1.jar
COPY ${JAR_FILE} api-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-0.0.1.jar"]