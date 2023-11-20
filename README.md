Scheduler Program

To run tests use 'mvn test'

I had an issue where I got the following error when trying to run any MAVEN
related commands:

The JAVA_HOME environment variable is not defined correctly
This environment variable is needed to run this program
NB: JAVA_HOME should point to a JDK not a JRE

This issue was fixed by doing "export JAVA_HOME='/usr/lib/jvm/java-11-openjdk-amd64'"
