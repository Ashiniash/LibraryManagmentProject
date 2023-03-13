package org.example.service.implementation;

import org.example.dao.repository.BooksRepository;
import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.CartRepository;
import org.example.dto.BookDTO;
import org.example.model.Book;
import org.example.model.Cart;
import org.example.model.CartDetail;
import org.example.service.BooksService;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
    CartService cartService;
    private static final String BOOK_LIST = "bookList";
    private static final String MY_ORDERS = "myOrders";

    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }


    @Override
    public List<String> getAllGenre() {
        return booksRepository.findAllGenre();
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
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(cart.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = findById(cartDetails.getBookId());
                    newBookList.add(book);
                }
            }
        }
        bookList.removeAll(newBookList);
        mav.setViewName("displayBooks");
        mav.addObject("uniqueGenres", uniqueGenres);
        mav.addObject(BOOK_LIST, bookList);
        mav.addObject("newCartList", newCartList);
        mav.addObject(USERID, userId);
        return mav;
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

    @Override
    public ModelAndView viewBooks(int cartId, int userId) {
        ModelAndView mav = new ModelAndView(MY_ORDERS);
        List<Book> newBookList = new ArrayList<>();
        List<Cart> cartList = cartRepository.displayMyOrdersByCartId(cartId, userId);
        for (Cart carts : cartList) {
            List<CartDetail> cartDetail = cartDetailRepository.findByCartId(carts.getCartId());
            for (CartDetail cartDetails : cartDetail) {
                Book book = findById(cartDetails.getBookId());
                newBookList.add(book);
            }
        }
        mav.addObject(BOOK_LIST, newBookList);
        mav.addObject("book", new Book());
        return mav;
    }

    @Override
    public ModelAndView displayCartsBooks(int userId) {
        try {
            Cart cart = cartService.getCartByStatus(userId, String.valueOf(orderStatus.PENDING));
            List<Cart> cartList = cartRepository.displayMyOrdersByStatus(userId, String.valueOf(orderStatus.PENDING));
            List<Cart> newCartList = new ArrayList<>();
            List<Book> newBookList = new ArrayList<>();
            for (Cart carts : cartList) {
                newCartList.add(cartRepository.findCartsById(carts.getCartId(), carts.getUserId()));
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(carts.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = findById(cartDetails.getBookId());
                    newBookList.add(book);
                }
            }
            ModelAndView modelAndView = new ModelAndView("displayCartBooks");
            modelAndView.addObject(BOOK_LIST, newBookList);
            modelAndView.addObject("cart", newCartList);
            modelAndView.addObject("cartId", cart.getCartId());
            return modelAndView;
        } catch (NullPointerException e) {
            return new ModelAndView("nullCart");
        }
    }

    @Override
    public ModelAndView returnBooks(int cartId, int userId) {
        ModelAndView mav = new ModelAndView();
        List<Cart> cartList = cartRepository.displayMyOrdersByCartId(cartId, userId);
        List<CartDetail> delayedBookList = new ArrayList<>();
        for (Cart carts : cartList) {
            List<CartDetail> cartDetail = cartDetailRepository.findByCartId(carts.getCartId());
            for (CartDetail cartDetails : cartDetail) {
                LocalDate todayDate = LocalDate.now();
                if (cartDetails.getReturnDate().toLocalDate().isBefore(todayDate)) {
                    delayedBookList.add(cartDetails);
                }
            }
        }
        cartService.returnBook(cartId, userId);
        mav.addObject("delayedBookList", delayedBookList);
        mav.setViewName("orderReturnedSuccess");
        return mav;
    }

    @Override
    public ModelAndView searchBook(int bookId) {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("searchedBookId", bookId);
            Book book = findById(bookId);
            if (book != null) {
                mav.setViewName("displaySearchBook");
                mav.addObject("book", book);
            } else {
                mav.setViewName("noSuchId");
                mav.addObject("error", "Book not found");
            }
            return mav;
        } catch (NoSuchElementException noSuchElementException) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("noSuchId");
            return mav;
        }
    }


    @Override
    public ModelAndView displayPendingBooks(int userId) {
        ModelAndView mav = new ModelAndView(MY_ORDERS);
        List<Cart> newCartList = new ArrayList<>();
        List<Book> newBookList = new ArrayList<>();
        List<Cart> cartList = cartRepository.displayMyOrders(userId);
        for (Cart cart : cartList) {
            if (cart.getOrderStatus().equalsIgnoreCase("pending")) {
                newCartList.add(cartRepository.findCartById(cart.getCartId(), cart.getUserId()));
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(cart.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = findById(cartDetails.getBookId());
                    newBookList.add(book);
                    mav.addObject(BOOK_LIST, newBookList);
                    mav.addObject("cart", newCartList);
                }
            }
        }
        mav.addObject("book", new Book());
        return mav;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return booksRepository.findByGenre(genre);
    }
}






