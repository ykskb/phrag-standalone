FROM openjdk:11.0.14.1-jre-slim-buster

WORKDIR /

COPY target/phrag-standalone-0.1.0-standalone.jar phrag-standalone.jar
EXPOSE 3000

COPY db/dev.sqlite dev.sqlite

ENV JDBC_URL=jdbc:sqlite:dev.sqlite

CMD java -jar phrag-standalone.jar