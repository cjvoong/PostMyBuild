# PostMyBuild 

A simple practice application using Spring and Java, a rest endpoint which allows you to create build jobs.

You can also add addresses and builders.

Build on MVC pattern and uses a mysql backend, atm for development that has been added to a Docker compose file as an image, future considerations should include;

* How should the app be deployed?
    * AWS / Azure
    * Kubernetes
    
* How should the app be built?
    * Docker image
    * Code artifact or JAR
    
* Code design questions
    * Should we use so much spring?
    * Is it easy enough to swap out datastore? ALA repository pattern.
    
* Test questions
    * How to effective test each of the layers


Need to sort out logging
Need to sort how service to run the Jar
Automated deployment