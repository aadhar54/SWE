So today we are going to study Hibernate and connect our spring boot project to db using JPA . The implementation of JPA we are going to use is Hibernate . So lets begin..

so we have created a starter spring boot project and added all the required maven dependencies for hibernate jpa in our pom. Now next step is to creating the entity models for our project digital library.

We are going to create entity class and each entity class is going to become a table in our mysql data base. So lets begin. Which tables do you need think. 
So in a digital library, we will need a student, book. Each book will have an author. And what else..
When we will issue or return a book , we will do a Transaction . Each transaction needed to be saved in a Transaction table. What else...?
So finally we have 4 tables requirement . Author , Book , Student and Transaction. You might think we would need an admin or librarian but no we are writing a fully automated spring boot software product so need for Manual Intervention.

Our Frontend is going to show all the books availabele and their current availability. Student who is our end user will use the frontend interface to click on issue / return the book . Each student can only issue 3 books at max . Each transaction can only one contains only only one book. So if a student wants to issue 3 books then 3 separate transactions. Lets start creating the data model entity classes. 
