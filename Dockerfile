FROM openjdk:17
COPY target/dashboard-manager-backend-0.0.1-SNAPSHOT.jar dashboard-manager-backend.jar
ENTRYPOINT ["java","-jar","dashboard-manager-backend.jar"]