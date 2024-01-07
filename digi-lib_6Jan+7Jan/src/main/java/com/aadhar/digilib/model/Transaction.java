package com.aadhar.digilib.model;

import com.aadhar.digilib.model.enums.TransactionStatus;
import com.aadhar.digilib.model.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    // now lets think what all columns should our Transaction table contain ???
    // first of all each Transaction table should have a Primary Key id
    // Notice we will have a Primary Key id which will be internal to our system
    // we will also have an external txn id for each txn which will be alphanumeric to gve to customer
    // as unique id for his/her txn.

    // so how to create a PKey for our Txn table ?

    @Id // tells Hibernate to create this column as PKEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means for each txn row in our Transaction table , id will be auto incremented by DB
    private Integer id;

    // what else ?
    @Column(unique = true, nullable = false) // validation for our column
    private String externalId;

    // next what columns think..

    // when is the txn created on which date
    // now we want hibernate to add the time automatically whenever a new row is added in transaction table - HOW ?
    // using the @CreationTimestamp annotation
    @CreationTimestamp
    private Date transactionTime;

    // next what?

    @UpdateTimestamp
    private Date updatedOn;

    //each tranaction will have a type - either a RETURN txn or an ISSUE txn
    // so we are going to create an Enum for transcation Type just like we did for Genre ..
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;


    // OK so now we have created an enum for Type of Transaction. Then each transaction will have status right .
    // PENDING , FAILED and SUCCESS . we need a similar enum for this.
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    //here in the above two enums how we will tell hibernate that we want to store the string value ???
    // using the @Enumerated annotation


    // now thats out of the picture what else should we have in our transaction table??
    // we should a Foreign Key of Book table in our txn table

    // how to tell hibernate that this is FKEY ? - @JoinColumn
    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties("{transactionList}")
    private Book book;

    // now next ou r transaction table should also have the FKEY of our Student table

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"}) // this means we want to ignore the transactionList from the response of my_student during any
    //request
    private Student student;

    // now what else we should also have a fine rigth ??
    // fine can be like 10 Rs 50 paise so lets have it as a double
    private Double fine;

    // with this our transaction entity is also completed.

}
