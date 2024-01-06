package com.aadhar.digilib.service;

import com.aadhar.digilib.dto.CreateBookRequest;
import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    // what annotation are we going to give it to make it a service bean ??
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    // for creating a book we will be recieving a request from the controller . The type of that request will be CreateBookRequest
    public Book create(CreateBookRequest createBookRequest){
        // first of all get an author out of book object so create a book object from the create Book request dto object
        Book book = createBookRequest.to();
        Author author = authorService.createOrGet(book.getMy_author());

        // now set the returned or created author to the book entity object
        book.setMy_author(author);

        return bookRepository.save(book);

    }


    //lets proceed to assign book to student
    public void assignBookToStudent(Book book, Student student){
        //lets go back to Book repository and create this method to assign book to student
        bookRepository.
    }

}
