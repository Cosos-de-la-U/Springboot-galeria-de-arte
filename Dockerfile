FROM eclipse-temurin:17-alpine

RUN mkdir /app

WORKDIR /app

COPY target/ArtGallery-0.0.1-SNAPSHOT.war /app/welcome.war

EXPOSE 8080

CMD java -jar welcome.war