package com.aadhar.digilib.service;

import com.aadhar.digilib.dto.SearchBookRequest;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.model.Transaction;
import com.aadhar.digilib.model.enums.TransactionStatus;
import com.aadhar.digilib.model.enums.TransactionType;
import com.aadhar.digilib.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${student.issue.max_books}")
    private int maxBooksForIssuance;

    @Value("${student.issue.number_of_days}")
    private int numberOfDaysForIssuance;

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

    public String issueTxn(String bookName, int studentId) throws Exception {
        // find all the available books with the book name
        List<Book> bookList;

        try{
            bookList = bookService.search(
                    SearchBookRequest.builder()
                            .searchKey("name")
                            .searchValue(bookName)
                            .operator("=")
                            .available(true)
                            .build()
            );
        }
        catch(Exception e){
            throw new Exception("Book not found");
        }

        // find the student by id
        Student student = studentService.get(studentId);

        // Validations
        if(student.getBookList() != null && student.getBookList().size() >= maxBooksForIssuance){
            throw new Exception("Book Limit Reached");
        }

        if(bookList.size() == 0){
            throw new Exception("Not able to find any book in the library");
        }

        Book book = bookList.get(0);

        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

       transaction = transactionRepository.save(transaction);

       try{
           book.setMy_student(student);
           bookService.assignBookToStudent(book, student);

           transaction.setTransactionStatus(TransactionStatus.SUCCESS);
       }catch(Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
           return transactionRepository.save(transaction).getExternalId();
       }

    }

    public String returnTxn(int bookId, int studentId) throws Exception {
        Book book;

        try{
            book = bookService.search(
                    SearchBookRequest.builder()
                            .searchKey("id")
                            .searchValue(String.valueOf(bookId))
                            .operator("=")
                            .build()
            ).get(0);
        }
        catch(Exception e){
            throw new Exception("not able to fetch the book using book id");
        }

        // Validation
        if(book.getMy_student() != null && book.getMy_student().getId() != studentId){
            throw new Exception("Book is not assigned to this student");
        }

        Student student = studentService.get(studentId);

        Transaction transaction = Transaction.builder()
                .externalId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transaction = transactionRepository.save(transaction); // the transaction object returned from .save() is coming from db


        // Fine Calculation
        Transaction lastIssueTransaction = transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(student,book,
                TransactionType.ISSUE, TransactionStatus.SUCCESS);

        long issueTxnInMillis = lastIssueTransaction.getTransactionTime().getTime();

        long currentTimeInMillis = System.currentTimeMillis();

        long timeDifferenceInMillis = currentTimeInMillis - issueTxnInMillis;

        // task is to convert this time difference from millis to days
        // because for each day we are charging Rs 10

        long timeDifferenceInDays = TimeUnit.DAYS.convert(timeDifferenceInMillis, TimeUnit.MILLISECONDS);

        Double fine = 0.0;
        if(timeDifferenceInDays > numberOfDaysForIssuance){
            long daysEligibleForFine = timeDifferenceInDays - numberOfDaysForIssuance;
            fine = daysEligibleForFine * 10.0;
        }

        // add the fine to the transaction and from these we add it to next semeester fees of this student
        transaction.setFine(fine);


        try {
            // finally return the book and remove the student id from the book
            book.setMy_student(null);
            bookService.unassignBookFromStudent(book);

            // we are sure that we can mark this transaction as success in the table
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            return transaction.getExternalId();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        finally {
            // update the transaction row in transaction table
            transactionRepository.save(transaction);
            return transaction.getExternalId();
        }
    }
}
