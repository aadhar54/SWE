package com.aadhar.digilib.controller;

import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.service.AuthorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // now what all I need here
    // I need to consume the methods created in my service class ie AuthorService

    // I want to make the below method as a controller method so for that I will create a GetMapping annotation over this method
    @GetMapping("/author/all")
    public List<Author> getAuthors(){
        return authorService.findAll();
    }

}
