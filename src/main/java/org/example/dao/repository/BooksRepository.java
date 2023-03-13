package org.example.dao.repository;

import org.example.model.Book;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT book.genre FROM Book book ")
    List<String> findAllGenre();
    @Query("SELECT book FROM Book book WHERE book.genre=:genre")
    List<Book> findByGenre(@Param("genre")String genre);


}

