package com.aadhar.digilib.service;

import com.aadhar.digilib.dto.CreateBookRequest;
import com.aadhar.digilib.dto.SearchBookRequest;
import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.model.Transaction;
import com.aadhar.digilib.model.enums.Genre;
import com.aadhar.digilib.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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


    //lets proceed to assign book to student - issue
    public void assignBookToStudent(Book book, Student student){
        //lets go back to Book repository and create this method to assign book to student
        bookRepository.assignBookToStudent(book.getId(), student);
    }

    // method to unassign the book - return
    public void unassignBookFromStudent(Book book){
        bookRepository.unassignBook(book.getId());
    }

    //delete api
    public Book delete(int bookId){
        try{
            Book book = bookRepository.findById(bookId).orElse(null);
            List<Transaction> txnList = book.getTransactionList();
            bookRepository.deleteById(bookId);
            return book;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<Book> search(SearchBookRequest searchBookRequest) throws Exception {
        switch(searchBookRequest.getSearchKey()){
            case "name":
                if(searchBookRequest.isAvailable()){
                    return bookRepository.findByNameAndmy_studentIsNull(searchBookRequest.getSearchValue());
                }
                return bookRepository.findByName(searchBookRequest.getSearchValue());
            case "genre":
                return bookRepository.findByGenre(Genre.valueOf(searchBookRequest.getSearchValue()));
            case "id":
                Book book = bookRepository.findById(Integer.parseInt(searchBookRequest.getSearchValue())).orElse(null);
                return Arrays.asList(book);
            default:
                throw new Exception("invalid search key");

        }
    }

    public List<Book> get(){
        return bookRepository.findAll();
    }


}
