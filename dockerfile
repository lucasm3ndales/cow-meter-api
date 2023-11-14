FROM openjdk:17
ADD target/cow-meter-api.jar cow-meter-api.jar
ENTRYPOINT ["java", "-jar", "cow_meter_api.jar"]
EXPOSE 8080
