FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/social_media-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV DATASOURCE_URL=jdbc:postgresql://db:5432/social_media
ENV DATASOURCE_USERNAME=postgres
ENV DATASOURCE_PASSWORD=20092002
ENTRYPOINT ["java","-jar","/app.jar"]