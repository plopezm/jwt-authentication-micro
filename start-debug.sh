#!/bin/bash
mvn clean package
java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 app/target/app-0.1.0-swarm.jar