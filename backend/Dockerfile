FROM openjdk:17
COPY . /app
WORKDIR /app
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/*.jar"]
