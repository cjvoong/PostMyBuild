# PostMyBuild 

A simple practice application using Spring and Java, a rest endpoint which allows you to create build jobs.

You can also add addresses and builders.

Build on MVC pattern and uses a mysql backend, atm for development that has been added to a Docker compose file as an image, future considerations should include;

# Deployment

## pre-reqs

Server with java 17 installed
Service setup for running the jar with a logback.xml and application.config file
Log location set to /var/log/postmybuild.log

## running

Github action that automatically builds, tests (not restassured at this stage), and deploys to a server that runs the jar, no apache

# Locally

Run in intellij or

`java -jar service-0.0.1-SNAPSHOT.jar`