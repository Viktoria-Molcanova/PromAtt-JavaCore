
FROM openjdk:11-jdk
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn javadoc:javadoc
CMD ["mvn", "javadoc:javadoc"]

