package com.aadhar.digilib.model;

import com.aadhar.digilib.model.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.net.Authenticator;
import java.util.Date;
import java.util.List;

@Entity // for mapping this Java class and telling Hibernate to create Book table in db
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // for creating the java object of Book class using Builder pattern
public class Book {

    /*
    IDENTITY -> DB will auto increment
    UUID -> string
    SEQUENCE -> oracle db
    TABLE -> not used because not thread safe operation
    AUTO -> auto increment is managed by hibernate.
     */
    @Id // tells hibernate that this is our primary key of Book table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for telling MySQL db to auto increment id everytime a new Book row is created in Book table
    private int id;

    private String name;

    // each genre enum will have one string value and one integer value(ordinal)
    // we have to tell Hibernate which value we want to store in table against the Genre column in Book table
    @Enumerated(value = EnumType.STRING) // we will store String value of our enum in our Book table
    private Genre genre; // we will create an enum for Genre because in our library we only have three genres of Books and dont want any manual mistake

    private Integer pages;

    // now author id is going to be a foreign key in our Book table
    // how do we tell hibernate about our foreign key of author id in the book table ? Ans -> using @JoinColumn
    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"bookList"})
    private Author my_author;

    // similarly we are going to have student id also as foreign key in our Book table
    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"bookList"})
    private Student my_student;

    // now we also want to store the Date when the book is created and updated . Hibernate helps us do this automatically by using annotations

    @CreationTimestamp
    private Date createdOn;

    // Similarly for updated On Date
    @UpdateTimestamp
    private Date updatedOn;

    // now lastly we also want to have bidirectional relationship with Transaction table
    // observe that we are not going to put @JoinCOlumn on the below which means we dont want to store it in our Book table as Foreign Key

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER) // because bidirectional relationship between Book and Transaction entity classes
    //secondly we can also tell Hibernate that how to load this list. DO we want to load that on all requests then use FetchType as EAGER
    // if we want to load it only when it is explicitly asked for we can use Fetch Type as LAZY
    // By default its Fetch Type as LAZY . So to demonstrate that how to load it on every request related to Book table then we can tell
    // hibernate to load it EAGERLY. using FetchType.EAGER in the above annotation.
    @JsonIgnoreProperties({"book"})
    private List<Transaction> transactionList;

    //and with this our entity classes for Book and Student are completed. Now I will move on to Genre Enum and show how we can assign values to ENum
}
