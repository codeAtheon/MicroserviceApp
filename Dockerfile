FROM ibmjava:8-sfj
LABEL maintainer="IBM Java Engineering at IBM Cloud"

COPY build/libs/microservices-poc*.jar /microservices-poc.jar

ENV JAVA_OPTS=""
ENV WEB_PORT 80
EXPOSE  80

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /microservices-poc.jar" ]
