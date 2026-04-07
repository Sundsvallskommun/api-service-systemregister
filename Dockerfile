FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn package -DskipTests -q

FROM eclipse-temurin:25-jre
WORKDIR /app

RUN useradd -u 10001 -r -s /usr/sbin/nologin appuser

COPY --from=build /build/target/*.jar /app/app.jar

RUN chown -R appuser:appuser /app
USER appuser

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=25 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
