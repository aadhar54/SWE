package com.aadhar.digilib.dto;

import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.enums.Genre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank // these all are validations for the request sent from the frontend client to our backend.
    private String name;

    @NotNull // these all are validations for the request sent from the frontend client to our backend.
    private Genre genre;

    private Integer pages;

    @NotBlank // these all are validations for the request sent from the frontend client to our backend.
    private String authorName;

    @NotBlank // these all are validations for the request sent from the frontend client to our backend.
    private String authorCountry;

    @NotBlank
    @Email
    private String authorEmail;


    //lets write a method to convert CreateBookRequest object to a bool object as ultimately I will be storing a book object in db
    public Book to(){
        return Book.builder()
                .name(this.name)
                .pages(this.pages)
                .genre(this.genre)
                .my_author(
                        Author.builder()
                                .name(this.authorName)
                                .country(this.authorCountry)
                                .email(this.authorEmail)
                                .build()
                ) //here we are going to build an author object from the Create Book request attributes
                .build();
    }


    // so finally our Create Book request DTO class is complete
    // lets go back to Book Service now

}
