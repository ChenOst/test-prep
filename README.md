# test-prep
## Introduction
The project is to provide a solution for students studying in Ariel University's heritage courses.
Every student at Ariel University is required to take a number of heritage courses amounting to 12
 credit points as part of the first degree. Most courses contains multiple choice tests.
 
 The project allows students a unique way of learning. Given a file with questions,
  the software builds for the student a test with several questions according to his
   choice and returns a score according to his success.
   
![](demonstration.gif)

## User Connection
After you download/ clone the repository make sure to change the `Application.properties` file accordingly to your information.

This is how it looks:
```
spring.jpa.hibernate.ddl-auto=update
my-properties.path= puth you docx path over here
my-properties.numberOfQuestions= choose the number of question in each test
```
**NOTE: don't change the first line!**

You only need to change the second and the third lines.
For Example:
```
spring.jpa.hibernate.ddl-auto=update
my-properties.path=C:\\Users\\Chen\\Desktop\\test.docx
my-properties.numberOfQuestions=5
```
### Docx parser
In order for the program to work the file should be in a specific form.

<img src="Readme/fileExample.png"> 