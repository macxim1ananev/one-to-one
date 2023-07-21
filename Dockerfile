FROM gradle:jdk17

COPY ./ ./

RUN ./gradlew build -x test

RUN ./gradlew flywayMigrate

CMD ["java", "-jar", "build/libs/one-to-one-0.0.1-SNAPSHOT.jar"]

