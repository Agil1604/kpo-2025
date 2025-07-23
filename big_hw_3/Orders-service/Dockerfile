FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts* ./
COPY gradle ./gradle
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle dependencies --no-daemon
COPY --chown=gradle:gradle src ./src
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle build -x test --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

