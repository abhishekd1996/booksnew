FROM openjdk:8

RUN apt-get update && apt-get install -y netcat

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh
COPY build/libs/book-store*.jar /
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENV SERVER_PORT="8080"
CMD /entrypoint.sh
