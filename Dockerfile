FROM amazoncorretto:21-alpine

COPY infrastructure/build/libs/*.jar /opt/app/application.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/application.jar

CMD ["java \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    -jar /opt/app/application.jar"]