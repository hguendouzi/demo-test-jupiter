FROM openjdk:11
MAINTAINER Hicham GUENDOUZI (guendouzi.ing@gmail.com)
ADD ./target/demo-0.0.1-SNAPSHOT.jar /demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/demo.jar"]
