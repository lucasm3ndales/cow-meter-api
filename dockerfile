FROM openjdk:17
ADD target/cow_meter_api.jar cow_meter_api.jar
ENTRYPOINT ["java", "-jar", "cow_meter_api.jar"]
EXPOSE 8080
