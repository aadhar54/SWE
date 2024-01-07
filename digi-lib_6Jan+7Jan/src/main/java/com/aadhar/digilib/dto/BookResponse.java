package com.aadhar.digilib.dto;

import com.aadhar.digilib.model.Author;
import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.model.enums.Genre;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private int id;
    private String name;
    private Genre genre;
    private Integer pages;
    private Author my_author;
    private Student my_student;

    private Date createdOn;
    private Date updatedOn;

    // book -> bookResponse
    public static BookResponse from(Book b){
        return BookResponse.builder()
                .id(b.getId())
                .genre(b.getGenre())
                .createdOn(b.getCreatedOn())
                .updatedOn(b.getUpdatedOn())
                .pages(b.getPages())
                .my_author(b.getMy_author())
                .my_student(b.getMy_student())
                .name(b.getName())
                .build();
    }


}
