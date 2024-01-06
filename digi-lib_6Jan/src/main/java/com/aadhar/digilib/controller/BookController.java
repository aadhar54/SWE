package com.aadhar.digilib.controller;


import com.aadhar.digilib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// now guys lets create the controller for Book crud apis
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
}
