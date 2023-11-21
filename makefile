compile:
	mvn compile

package:
	mvn package

run-package:
	java -jar target/coo-1.0.jar

run:
	mvn exec:java

mutation:
	mvn test-compile org.pitest:pitest-maven:mutationCoverage

test:
	mvn test

clean:
	mvn clean