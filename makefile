compile:
	mvn compile

package:
	mvn package

run-package:
	java -jar target/coo-1.0.jar

run:
	mvn exec:java -Dexec.args="0"

runInstantly:
	mvn exec:java -Dexec.args="1"

mutation:
	mvn test-compile org.pitest:pitest-maven:mutationCoverage

test:
	mvn test

push:
	git push -u origin main; git push -u gitlab main

clean:
	mvn clean