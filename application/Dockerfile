FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

CMD ["java", "-jar", "./build/libs/demo-0.0.1-SNAPSHOT.jar"]