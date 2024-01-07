package com.aadhar.digilib.service;

import com.aadhar.digilib.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TransactionRepository transactionRepository;

    // HW and practice

    // initiateTxn () -> book id, student id, type of txn

    /**
     * Issuance
     *  1. Get the book details and student details // here we are checking whether book is available or not
     *  2. Validation //
     *  3. Create a txn with pending status
     *  4. Assign the book to that particular student // update book set student_id = ? // here we are checking whether book is available or not
     *  5. Update the txn accordingly with status as SUCCESS or FAILED
     */


    /**
     * Return
     *  1. Create a txn with pending status
     *  2. Check the due date and correspondingly evaluate the fine
     *  3. Unassign the book from student // UPDATE BOOK table set student_id = null
     *  4. Update the txn accordingly with status as SUCCESS or FAILED
     */
}
