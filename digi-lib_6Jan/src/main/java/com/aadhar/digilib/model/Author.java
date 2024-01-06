package com.aadhar.digilib.model;

// this will be our entity class for Author table in db
//lets go back to Book and complete the java class
// so each book will have one author
// one author can write many books
//Author : Book  = One to Many
// Book : Author = Many to One

// so how are you going to create this Author java class as table in your mysql table
// by using the @Entity annotation given by hibernate . It tells Spring Boot hibernate to create a table for us for the class on which its annotated

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    //public Author(){} // no args contructor

    // now think which all columns should your Author table have

    //each author should have a primary key ID
    // how to tell sprng boot hibernate that this is the Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this tells mysql table to auto increment it whenever any new author row is added in table
    private Integer id;

    // now else author can have think pls..
    @Column(unique = true, nullable = false) // vaidation added for email
    private String email;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    // what else think...

    private String country;

    // now each author will have bidirectional relationship with Book table
    // but we dont want to store Book id as foreign key in Author table
    // so you will observe that we wont be using the @JoinColumn annotation like we used in Book table
    @OneToMany(mappedBy = "my_author") // see this mappedBy is for bidirectional relationship with the Book table. Book table has FKEY of author id.
    private List<Book> bookList;

    // now our book entity class is completed so we can move towards next entity class which is Transaction.
}
