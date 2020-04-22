# RD Electronics Webpage testing

## Technologies & tools
Java 11, Allure, Junit 5, Maven, Selenide, Lombok

## Run options

#### Run all tests                
mvn test 

#### Run tests and open allure report 
mvn clean test; mvn allure:serve

#### Run tests in background and open allure report 
mvn clean test -Dselenide.headless=true; mvn allure:serve

#### Run a single test method from a test class.
mvn -Dtest=TestApp1#methodName test   
Example: mvn -Dtest=RDWebPageTest#historyProductTest test

#### Run a single test class.
mvn -Dtest=HistoryViewTest test

#### Run multiple test classes.
mvn -Dtest=CartTest,HistoryViewTest test

## Parameters

#### Headless mode
-Dselenide.headless=true

#### Rerun failing tests
-Dsurefire.rerunFailingTestsCount=2