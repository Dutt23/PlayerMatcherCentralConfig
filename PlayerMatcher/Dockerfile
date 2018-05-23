FROM java:8
VOLUME /tmp
ARG JAR_FILE
ADD target/PlayerMatcher-1.0.jar target/app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]