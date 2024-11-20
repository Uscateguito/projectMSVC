# Usar una imagen base de Maven para compilar el proyecto
FROM maven:3.9.9-ibm-semeru-23-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usar una imagen base de Java para ejecutar el microservicio
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
