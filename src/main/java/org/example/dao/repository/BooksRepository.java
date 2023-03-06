package org.example.dao.repository;

import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT book.genre FROM Book book ")
    List<String> findAllGenre();

    @Query("SELECT book.genre FROM Book book ")
    List<Book> findBookByStatus();



}

