package com.aadhar.digilib.repository;

import com.aadhar.digilib.model.Book;
import com.aadhar.digilib.model.Student;
import com.aadhar.digilib.model.enums.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.name = :name and b.my_student is null")
    List<Book> findByNameAndmy_studentIsNull(String name);

    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);

    @Modifying // for Data manipulation in db
    @Transactional // for updating any data in db and have option of rollback if the db operation is not successful
    @Query("update Book b set b.my_student = ?2 where b.id = ?1 and b.my_student is null")
    void assignBookToStudent(int bookId, Student student);

    @Modifying
    @Transactional
    @Query("update Book b set b.my_student = null where b.id = ?1 ")
    void unassignBook(int bookId);
}
