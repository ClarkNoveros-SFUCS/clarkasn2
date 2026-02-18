# Testing

I will guide you on how to run the tests, what to expect, and what each test mean. I created two folders for it. 
* controllers
* repository

## Repository and Persistence Tests
In this section I used JPA and H2 database to test persistence and repo functionalities. 
I tested save and delete, which are methods used in the controllers, I take this oppurtunity to test 
whether objects are really saved and deleted in my database. Secondly, I also test if the correct roleType is attached, since this leads to my OOP functionality. Third, is my getAverageScore method that computes the correct average based on the integer fields.

## Getting Started

### Where is it?
* test/java/repository/Clarkasn2RepoTests.java

### What are the tests?
According to Teddy Smith, when doing testing in JPA use this: 
Arrange(Setup variables of the entity), Act(save), Assert(Assert the expected information must be the output)

* RoleType Validation (for my OOP functionality)
* OOP Functionality checks for Prof and Instructor
* Averaging Function
* Save and Check
* Delete and confirm deleted

### How to run
* Go to the file path mentioned above: test/java/repository/Clarkasn2RepoTests.java
* Run this command
```
mvn -Dtest=Clarkasn2ControllerTests test
```
* You can also press the play button beside each @Test annotation to check individually
### What to expect 
* Since we have 6 tests, Test Run must be == 6 
* See a "BUILD SUCCESS" message

## Controller Tests
In this section I used MockMvc to do testing on the validation, HTTP code, and path routing and redirection. I also used Jakarta validation packages and annotations for my validations. These validation annotations can be found in the Users.java file for each field. Found errors are stored in BindingResult. 

## Getting Started

### Where is it?
* test/java/repository/Clarkasn2ControllerTests.java

### What are the tests?

* Missing name (Blank)
* Email error (invalid format, no: '@' or '.')
* Integer value out of range (Niceness)
* Details page redirection to correct uid after creation
* Users stay at form if error persists

### How to run
* Go to the file path mentioned above: test/java/repository/Clarkasn2ControllerTests.java
* Run this command
```
mvn -Dtest=Clarkasn2ControllerTests test
```
* You can also press the play button beside each @Test annotation to check individually
### What to expect 
* Since we have 6 tests, Test Run must be == 6 
* See a "BUILD SUCCESS" message

## Run all tests files
* Run this command
```
mvn test
```
* There are total of 13 tests runs because there is a default Spring Boot test: Clarkasn2ApplicationTests.java
* See a "BUILD SUCCESS" message after.

ReadME format:
https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc#file-readme-template-md

