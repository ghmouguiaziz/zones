FROM openjdk:17-alpine
EXPOSE 8090
ADD target/Gestion_des_zones-0.0.1-SNAPSHOT.jar zones.jar
ENTRYPOINT ["java","-jar","zones.jar"]
