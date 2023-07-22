FROM gradle:jdk17 AS GRADLEW_BUILD

WORKDIR /app

COPY ./ /app

RUN ./gradlew build -x test

FROM openjdk:17

COPY --from=GRADLEW_BUILD /app/build/libs/one-to-one-0.0.1-SNAPSHOT.jar /app/one-to-one.jar

CMD ["java", "-jar", "/app/one-to-one.jar"]