package org.example.service;

import org.example.dto.BookDTO;
import org.example.model.Book;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BooksService {
    List<String> getAllGenre();

    Book getBookById(int bookId);


    ModelAndView displayCustomBooks(int userId);

    List<Book> getAllBooks(int userId);

    List<Book> getBookList(int userId);

    Book findById(int bookId);

    void addBook(BookDTO bookDTO);

    List<Book> getAllBook();

    Book deleteByBookId(int bookId);
}


