FROM maven:3.9.9-eclipse-temurin-21 AS base
WORKDIR /app
COPY pom.xml .
COPY src ./src

FROM base AS build
RUN mvn -q test && mvn -q -DskipTests package

FROM eclipse-temurin:21-jre-alpine AS runtime
RUN apk add --no-cache curl
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /app/target/product-crud-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
