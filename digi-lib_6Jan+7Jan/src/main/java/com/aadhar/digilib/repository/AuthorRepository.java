package com.aadhar.digilib.repository;

import com.aadhar.digilib.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// we have to give JPARepository< Entity name , Entity Primary Key Datatype>
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // thats all we have to give here
    // hibernate automatically creates the methods for us to interact with db
    // thats why companies today use hibernate

    // lets go back to Author Service and autowire the AuthorRepository and use its methods

    // we are basically telling hibernate that create a method for us in Repositoty
    // the best part is we dont have to give the implementation
    // only the return type and arguments work
    Author findByEmail(String email);
}
