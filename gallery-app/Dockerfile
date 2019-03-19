#build stage
FROM maven:3.6.0-jdk-11 AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests


#deploy stage
FROM tomcat:9.0.16-jre11
RUN mkdir $CATALINA_HOME/tmpFiles && chmod 777 $CATALINA_HOME/tmpFiles
COPY samples $CATALINA_HOME/tmpFiles

RUN rm -rf $CATALINA_HOME/webapps/ROOT
COPY --from=build /usr/src/app/target/gallery-app.war $CATALINA_HOME/webapps/ROOT.war