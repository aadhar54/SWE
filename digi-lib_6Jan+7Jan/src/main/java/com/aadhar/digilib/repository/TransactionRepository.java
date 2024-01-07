package com.aadhar.digilib.repository;

import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.model.Transaction;
import com.aadhar.digilib.model.enums.TransactionStatus;
import com.aadhar.digilib.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // find transaction by student and book and type and staus -> order it desc of transaction tim
    Transaction findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(Student student, Book book,
                                                                                                        TransactionType transactionType,
                                                                                                        TransactionStatus transactionStatus);
}
