package org.example.service;

import org.example.dto.BookDTO;
import org.example.model.Book;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BooksService {
    List<String> getAllGenre();

    ModelAndView displayCustomBooks(int userId);

    Book findById(int bookId);

    void addBook(BookDTO bookDTO);

    List<Book> getAllBook();

    Book deleteByBookId(int bookId);

    ModelAndView viewBooks(int cartId, int userId);

    ModelAndView displayCartsBooks(int userId);

    ModelAndView returnBooks(int cartId, int userId);


    ModelAndView searchBook(int bookId);

    ModelAndView displayPendingBooks(int userId);

    List<Book> findByGenre(String genre);
}


