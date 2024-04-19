FROM openjdk:21-jdk

#COPY target/VCR-0.0.1-SNAPSHOT.jar .

COPY  /build/libs/vcr-project-security-0.0.1-SNAPSHOT.war .
EXPOSE 8080

CMD ["java", "-jar", "vcr-project-security-0.0.1-SNAPSHOT.war"]