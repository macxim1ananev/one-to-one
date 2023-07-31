FROM gradle:jdk17

COPY ./build/libs/one-to-one-0.0.1-SNAPSHOT.jar /one-to-one.jar

CMD ["java", "-jar", "/one-to-one.jar"]