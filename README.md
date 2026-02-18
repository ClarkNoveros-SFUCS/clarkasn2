# Project Title

This is for my 276 Assignment 2. SFU School of CS Teaching Staff Rating Website.

## Description
This project followed the CRUD principle, which allow users to Create, Read, Update, and Delete. In this case users have full ability, zero abstraction to play with ratings and comments for the SFU CS Teaching Staff. 

I used a Spring Boot framework, PostrgreSQL database, Docker for the container, Thymeleaf for easy templating, Bootstrap for CSS and HTML; overall front-end development framework, and last but not the least, Render for my hosting services.

I also defined a style.css file for the custom colors that I found opensource from SFU, to get access to SFU's colour scheme. Find it over here at https://www.sfu.ca/communicators-toolkit/brand/guidelines/colours.html 

### OOP Feature
I defined a method called displayName, wherein if an entity is a Professor or Instructor, once the profile is created
I automatically append a title of Professor or Instructor, since in the html thymeleaf templating I call this method, and according to the roleType the correct corresponding method will be called == Polymorphism.

## Getting Started

### Dependencies
Following the tutorial by Prof. Bobby Chan these are the dependencies used
according to pom.xml file:
* postgresql
* spring boot starter validation and test
* h2 database 
* thymeleaf
* webmvc
* jpa 

Java Version: 17
Tried 17 out because this is the version I was seeing on the tutorials. Will make use of 21 again on the next project.

### Executing program
On local server: 
* Go to main/java
* There is a file named: Clarkasn2Application.java
* Above the line below, there will be a small text to "Run"
```
line 9: public static void main(String[] args)
```
* On terminal, run this command. 
```
mvn spring-boot:run
```

Using Render URL:
```
https://clarkasn2.onrender.com
```
* Do not forget to append: /users/index 
* This will show you my main landing page.

## Author
Created by:
Clark Darneill Noveros
cdn5@sfu.ca

## Acknowledgments and AI Declaration

Youtube:
* Professor Bobby Chan's Youtube Videos
* https://www.youtube.com/watch?v=jqwZthuBmZY
* https://www.youtube.com/watch?v=BZBFw6fBeIU&t=191s

ChatGPT:
* debugging purposes (it found mispells, wrong function and argument names)
* thymeleaf templating tips
* DataJpaTest guidance
* DataJpaTest libararies were not importing
* MockMvc how to use methods

ReadME format:
https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc#file-readme-template-md

