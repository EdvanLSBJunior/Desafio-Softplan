FROM maven:3.8.5-openjdk-17 as builder
ADD . .
RUN mvn package


FROM amazoncorretto:17-alpine-jdk AS base

ENV PORT=8080

ENV TZ=America/Sao_Paulo

COPY --from=builder target/*.jar app.jar

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java", "-Xms50m", "-Xmx100m", "-XX:+UseSerialGC","-jar", "app.jar"]

EXPOSE ${PORT}