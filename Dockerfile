FROM openjdk:12.0.2

EXPOSE 9080

ADD ./target/begin-with-springboot-jpa-0.0.1-SNAPSHOT.jar begin-with-springboot-jpa.jar

#RUN mkdir /etc/greeting/    
#WORKDIR /etc/greeting/
#VOLUME [ "/etc/greeting/" ]

ENTRYPOINT [ "java", "-jar", "/begin-with-springboot-jpa.jar" ]