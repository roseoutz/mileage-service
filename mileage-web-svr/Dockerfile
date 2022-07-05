FROM openjdk:11.0.4-jre-slim-buster

MAINTAINER roseoutz
LABEL title="Triple Mileage MS Server"
LABEL description="roseoutz made..."
ENV TZ="Asia/Seoul"
RUN apt-get update

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /var/log
EXPOSE 9201
ENV PROFILE docker
ARG JAR_FILE=build/libs/triple-mileage-ms-svr.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", \
            "-jar", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=${PROFILE}", \
            "-Djava.net.preferIPv4Stack=true", \
            "app.jar" ]
