# Step 1: Build jar
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY student-management /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Step 2: Run jar
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]