package com.aadhar.digilib.service;

import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // what all crud methods are needed in Author ?

    // create, fetch by id and fetch all

    public Author createOrGet(Author author){
        // so here we try to get author from db if its exists
        // else we create an author
        Author authorFromDB = this.authorRepository.findByEmail(author.getEmail());

        if(authorFromDB != null){
            return authorFromDB;
        }

        // if author from db is null ie does not exist , we will create a new one in our Author table in db and return the
        // newly created author
        return authorRepository.save(author);
    }

    // next method we need is to fetch all authors present as rows in the author table
    public List<Author> findAll(){
        return authorRepository.findAll(); // as this is a very common crud usecase we dont even have to write the abstarct method
        // in the Repository interface . Its given as a functionality to us by default.
    }

    //  so we have completed our author service and author respositoy
    //lets go back to controller and make use of it
}
