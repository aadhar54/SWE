package com.aadhar.digilib.controller;


import com.aadhar.digilib.dto.BookResponse;
import com.aadhar.digilib.dto.CreateBookRequest;
import com.aadhar.digilib.dto.SearchBookRequest;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// now guys lets create the controller for Book crud apis
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        // so we are sending this DTO to our service class which will convert it into book object and use book repository to save it to db
        return bookService.create(createBookRequest);
    }

    @DeleteMapping("/{bookId}")
    public BookResponse deleteBook(@PathVariable("bookId") int bookId){
        try{
             Book deletedBook = bookService.delete(bookId);
             return BookResponse.from(deletedBook);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookService.get();
    }


    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {
        return bookService.search(searchBookRequest);
    }

}
