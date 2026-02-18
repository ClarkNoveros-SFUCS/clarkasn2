FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /target/clarkasn2-0.0.1-SNAPSHOT.jar clarkasn2.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar","clarkasn2.jar" ]



