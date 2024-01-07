package com.aadhar.digilib.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity // to create a table for this model class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    // what all you want in student table
    // each variable or data member here will become a column in the student table


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // primary key of table so @Id

    private String name;

    private String contact;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "my_student") // BAck Reference Bidirectional mapping with Book class
    @JsonIgnoreProperties({"my_student"})
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student"})
    private List<Transaction> transactionList;

    private Date validity; // validity of library card

    //now lets go and work on the Book table entity java class

}

