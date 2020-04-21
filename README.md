# RD Electronics Webpage testing

## Technologies & tools
Java 11, Allure, Junit 5, Maven, Selenide

## Run options

#### Run all tests                
mvn test 

#### Run tests and open allure report 
mvn clean test; mvn allure:serve

#### Run a single test method from a test class.
mvn -Dtest=TestApp1#methodname test   
Example: mvn -Dtest=RDWebPageTest#historyProductTest test

#### Run a single test class.
mvn -Dtest=TestApp1 test

#### Run multiple test classes.
mvn -Dtest=TestApp1,TestApp2 test

## Parameters

#### Headless mode
-Dselenide.headless=true

#### Rerun failing tests
-Dsurefire.rerunFailingTestsCount=2