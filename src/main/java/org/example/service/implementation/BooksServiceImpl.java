package org.example.service.implementation;

import org.example.dao.repository.BooksRepository;
import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.CartRepository;
import org.example.dto.BookDTO;
import org.example.model.Book;
import org.example.model.Cart;
import org.example.model.CartDetail;
import org.example.service.BooksService;
import org.example.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

public class BooksServiceImpl implements BooksService {
    private static final String USERID = "userId";
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartDetailRepository cartDetailRepository;
    @Autowired
    CartDetailService cartDetailService;

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
        List<Cart> cartList = cartRepository.displayMyOrders(userId);
        List<Book> newBookList = new ArrayList<>();
        List<Cart> newCartList = new ArrayList<>();
        List<Book> bookList = booksRepository.findAll();
        for (Cart cart : cartList) {
            if ((cart.getOrderStatus().equals("APPROVED") || cart.getOrderStatus().equals("PENDING"))) {
                newCartList.add(cartRepository.findCartById(cart.getCartId(), cart.getUserId()));
               List <CartDetail> cartDetail = cartDetailRepository.findByCartId(cart.getCartId());
                for(CartDetail cartDetails:cartDetail){
                    Book book= findById(cartDetails.getBookId());
                    newBookList.add(book);}
            }
        }
        bookList.removeAll(newBookList);
        mav.setViewName("displayBooks");
        mav.addObject("uniqueGenres", uniqueGenres);
        mav.addObject("bookList", bookList);
        mav.addObject(USERID, userId);
        return mav;
    }


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




