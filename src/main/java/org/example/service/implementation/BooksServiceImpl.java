package org.example.service.implementation;

import org.example.dao.repository.BooksRepository;
import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.OrderBooksRepository;
import org.example.dto.BookDTO;
import org.example.model.Book;
import org.example.model.CartDetail;
import org.example.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

public class BooksServiceImpl implements BooksService {
    private static final String USERID = "userId";
    @Autowired
    BooksRepository booksRepository;

    @Autowired
    OrderBooksRepository orderBooksRepository;
    @Autowired
    CartDetailRepository cartDetailRepository;

    enum orderStatus {
        PENDING, APPROVED, RETURNED;
    }

    @Override
    public List<String> getAllGenre() {
        return booksRepository.findAllGenre();
    }


    @Override
    public Book getBookById(int bookId) {
        return booksRepository.findById(bookId).get();
    }
    @Override
    public ModelAndView displayCustomBooks(int userId) {
        ModelAndView mav = new ModelAndView();
        List<String> allGenres = getAllGenre();
        Set<String> setWithoutDuplicates = new HashSet<>(allGenres);
        List<String> uniqueGenres = new ArrayList<>(setWithoutDuplicates);
        List<CartDetail> myOrders = cartDetailRepository.displayMyOrders(userId);
        List<Book> newBookList = new ArrayList<>();
        List<Book> bookList = booksRepository.findAll();
        for (CartDetail cartDetail : myOrders) {
            if (cartDetail.getOrderStatus().equals("APPROVED") || cartDetail.getOrderStatus().equals("PENDING")) {
                newBookList.add(booksRepository.findById(cartDetail.getBookId()).get());
            }
        }
        bookList.removeAll(newBookList);
        mav.setViewName("displayBooks");
        mav.addObject("uniqueGenres", uniqueGenres);
        mav.addObject("bookList", bookList);
        mav.addObject(USERID, userId);
        return mav;
    }

//    @Override
//    public ModelAndView displayCustomBooks(int userId) {
//        ModelAndView mav = new ModelAndView();
//        List<String> allGenres = getAllGenre();
//        Set<String> setWithoutDuplicates = new HashSet<>(allGenres);
//        List<String> uniqueGenres = new ArrayList<>(setWithoutDuplicates);
//        List<OrderBook> myOrders = orderBooksRepository.displayMyOrders(userId);
//        List<Book> newBookList = new ArrayList<>();
//        List<Book> bookList = booksRepository.findAll();
//        for (OrderBook orderBook : myOrders) {
//            if (orderBook.getOrderStatus().equals("APPROVED") || orderBook.getOrderStatus().equals("PENDING")) {
//                newBookList.add(booksRepository.findById(orderBook.getBookId()).get());
//            }
//        }
//        bookList.removeAll(newBookList);
//        mav.setViewName("displayBooks");
//        mav.addObject("uniqueGenres", uniqueGenres);
//        mav.addObject("bookList", bookList);
//        mav.addObject(USERID, userId);
//        return mav;
//    }


    @Override
    public List<Book> getAllBooks(int userId) {
        return booksRepository.findAll();
    }

    @Override
    public List<Book> getBookList(int userId) {
        Book book = new Book();
        book.setBookId(book.getBookId());
        book.setBookTitle(book.getBookTitle());
        return booksRepository.findAll();
    }

    @Override
    public Book findById(int bookId) {
        return booksRepository.findById(bookId).get();
    }

    @Override
    public void addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookTitle(bookDTO.getBookTitle());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setGenre(bookDTO.getGenre());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setPublisher(bookDTO.getPublisher());
        booksRepository.save(book);
    }

    @Override
    public List<Book> getAllBook() {
        return booksRepository.findAll();
    }

    @Override
    public Book deleteByBookId(int bookId) {
        booksRepository.deleteById(bookId);
        return null;
    }


}




