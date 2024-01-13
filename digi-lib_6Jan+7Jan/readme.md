So today we are going to study Hibernate and connect our spring boot project to db using JPA . The implementation of JPA we are going to use is Hibernate . So lets begin..

so we have created a starter spring boot project and added all the required maven dependencies for hibernate jpa in our pom. Now next step is to creating the entity models for our project digital library.

We are going to create entity class and each entity class is going to become a table in our mysql data base. So lets begin. Which tables do you need think. 
So in a digital library, we will need a student, book. Each book will have an author. And what else..
When we will issue or return a book , we will do a Transaction . Each transaction needed to be saved in a Transaction table. What else...?
So finally we have 4 tables requirement . Author , Book , Student and Transaction. You might think we would need an admin or librarian but no we are writing a fully automated spring boot software product so need for Manual Intervention.

Our Frontend is going to show all the books availabele and their current availability. Student who is our end user will use the frontend interface to click on issue / return the book . Each student can only issue 3 books at max . Each transaction can only one contains only only one book. So if a student wants to issue 3 books then 3 separate transactions. Lets start creating the data model entity classes. 



----------
Mysql commands

C:\Users\aadha>mysql -u root -p
Enter password: ****
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 301
Server version: 8.2.0 MySQL Community Server - GPL

Copyright (c) 2000, 2023, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| knowledgehut       |
| mysql              |
| performance_schema |
| swecamp            |
| sys                |
+--------------------+
6 rows in set (0.01 sec)

mysql> use knowledgeHut;
Database changed
mysql> show tables;
+------------------------+
| Tables_in_knowledgehut |
+------------------------+
| author                 |
| book                   |
| student                |
| transaction            |
+------------------------+
4 rows in set (0.00 sec)

mysql> select * from author;
Empty set (0.00 sec)

mysql> select * from book;
Empty set (0.00 sec)

mysql> select * from tranasaction;
ERROR 1146 (42S02): Table 'knowledgehut.tranasaction' doesn't exist
mysql> select * from transaction;
Empty set (0.00 sec)

mysql> select * from student;
Empty set (0.00 sec)

mysql> describe book;
+---------------+---------------------------------------------+------+-----+---------+----------------+
| Field         | Type                                        | Null | Key | Default | Extra          |
+---------------+---------------------------------------------+------+-----+---------+----------------+
| id            | int                                         | NO   | PRI | NULL    | auto_increment |
| my_author_id  | int                                         | YES  | MUL | NULL    |                |
| my_student_id | int                                         | YES  | MUL | NULL    |                |
| pages         | int                                         | YES  |     | NULL    |                |
| created_on    | datetime(6)                                 | YES  |     | NULL    |                |
| updated_on    | datetime(6)                                 | YES  |     | NULL    |                |
| name          | varchar(255)                                | YES  |     | NULL    |                |
| genre         | enum('FICTION','NON_FICTION','PROGRAMMING') | YES  |     | NULL    |                |
+---------------+---------------------------------------------+------+-----+---------+----------------+
8 rows in set (0.00 sec)

mysql> describe student;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| created_on | datetime(6)  | YES  |     | NULL    |                |
| updated_on | datetime(6)  | YES  |     | NULL    |                |
| validity   | datetime(6)  | YES  |     | NULL    |                |
| contact    | varchar(255) | YES  |     | NULL    |                |
| name       | varchar(255) | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
6 rows in set (0.01 sec)

mysql> describe book;
+---------------+---------------------------------------------+------+-----+---------+----------------+
| Field         | Type                                        | Null | Key | Default | Extra          |
+---------------+---------------------------------------------+------+-----+---------+----------------+
| id            | int                                         | NO   | PRI | NULL    | auto_increment |
| my_author_id  | int                                         | YES  | MUL | NULL    |                |
| my_student_id | int                                         | YES  | MUL | NULL    |                |
| pages         | int                                         | YES  |     | NULL    |                |
| created_on    | datetime(6)                                 | YES  |     | NULL    |                |
| updated_on    | datetime(6)                                 | YES  |     | NULL    |                |
| name          | varchar(255)                                | YES  |     | NULL    |                |
| genre         | enum('FICTION','NON_FICTION','PROGRAMMING') | YES  |     | NULL    |                |
+---------------+---------------------------------------------+------+-----+---------+----------------+
8 rows in set (0.00 sec)

mysql> select * from student;
+----+----------------------------+----------------------------+----------------------------+-------------------+---------+
| id | created_on                 | updated_on                 | validity                   | contact           | name    |
+----+----------------------------+----------------------------+----------------------------+-------------------+---------+
|  1 | 2024-01-13 12:04:54.700000 | 2024-01-13 12:04:54.700000 | 2025-01-12 12:04:54.619000 | crystal@gmail.com | Crystal |
+----+----------------------------+----------------------------+----------------------------+-------------------+---------+
1 row in set (0.00 sec)

mysql> select * from Book;
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
| id | my_author_id | my_student_id | pages | created_on                 | updated_on                 | name      | genre   |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
|  1 |            2 |          NULL |   100 | 2024-01-13 12:10:22.874000 | 2024-01-13 12:10:22.874000 | Alchemist | FICTION |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
1 row in set (0.00 sec)

mysql> select * from Book;
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
| id | my_author_id | my_student_id | pages | created_on                 | updated_on                 | name      | genre   |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
|  1 |            2 |             1 |   100 | 2024-01-13 12:10:22.874000 | 2024-01-13 12:13:24.240000 | Alchemist | FICTION |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
1 row in set (0.00 sec)

mysql> select * from transaction;
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
| book_id | fine | id | student_id | transaction_time           | updated_on                 | external_id                          | transaction_status | transaction_type |
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
|       1 | NULL |  1 |          1 | 2024-01-13 12:13:24.184000 | 2024-01-13 12:13:24.290000 | 676d49a1-9453-4b73-9686-f9d1669d85fd | SUCCESS            | ISSUE            |
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
1 row in set (0.00 sec)

mysql> select * from transaction;
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
| book_id | fine | id | student_id | transaction_time           | updated_on                 | external_id                          | transaction_status | transaction_type |
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
|       1 | NULL |  1 |          1 | 2024-01-13 12:13:24.184000 | 2024-01-13 12:13:24.290000 | 676d49a1-9453-4b73-9686-f9d1669d85fd | SUCCESS            | ISSUE            |
|       1 |    0 |  2 |          1 | 2024-01-13 12:15:58.706000 | 2024-01-13 12:15:58.761000 | e22dca4a-0b44-4a3d-8466-879eb69424ab | SUCCESS            | RETURN           |
+---------+------+----+------------+----------------------------+----------------------------+--------------------------------------+--------------------+------------------+
2 rows in set (0.00 sec)

mysql> select * from Book;
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
| id | my_author_id | my_student_id | pages | created_on                 | updated_on                 | name      | genre   |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
|  1 |            2 |          NULL |   100 | 2024-01-13 12:10:22.874000 | 2024-01-13 12:15:58.742000 | Alchemist | FICTION |
+----+--------------+---------------+-------+----------------------------+----------------------------+-----------+---------+
1 row in set (0.00 sec)
