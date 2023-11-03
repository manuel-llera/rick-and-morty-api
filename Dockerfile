FROM eclipse-temurin:17-jdk-alpine
EXPOSE 3456
ARG JAR_FILE=target/rick-and-morty-api-final-name.jar
ADD ${JAR_FILE} rick-and-morty.jar
ENTRYPOINT ["java","-jar","/rick-and-morty.jar"]