package org.example.controller;

import org.example.dao.repository.BooksRepository;
import org.example.dto.BookDTO;
import org.example.dto.OrderBookDTO;
import org.example.dto.UserDTO;
import org.example.model.Book;
import org.example.model.OrderBook;
import org.example.model.User;
import org.example.service.BooksService;
import org.example.service.OrderBooksService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    @Autowired
    BooksService booksService;
    @Autowired
    OrderBooksService orderBooksService;
    @Autowired
    UserService userService;
    @Autowired
    private BooksRepository booksRepository;


    @RequestMapping(value = "/addBook")
    public ModelAndView addBook()  {
        ModelAndView mav = new ModelAndView("addBook");
        mav.addObject("book", new Book());
        return mav;
    }
    @PostMapping(value = "/saveBook")
    public ModelAndView saveBook(@ModelAttribute("user") BookDTO bookDTO) {
        booksService.addBook(bookDTO);
        return new ModelAndView("addBookSuccess");
    }
    @GetMapping(value = "/displayAllBooks")
    public ModelAndView displayAllBooks() {
        ModelAndView mav = new ModelAndView("displayAllBooks");
        List<Book> bookList = booksService.getAllBook();
        mav.addObject("bookList", bookList);
        mav.addObject("book", new Book());
        return mav;
    }
    @GetMapping(value = "/deleteBook/{bookId}")
    public ModelAndView deleteBook(@PathVariable int bookId) {
        ModelAndView mav = new ModelAndView("deleteBookSuccess");
        Book book = booksService.deleteByBookId(bookId);
        mav.addObject("book", book);
        return mav;
    }
    @GetMapping(value = "/displayAllBooksByStatus")
    public ModelAndView displayAllBooksByStatus() {
        ModelAndView mav = new ModelAndView("displayAllBooksByStatus");
        List<OrderBook> orderBooks = orderBooksService.getAllBookByStatus();
        mav.addObject("orderBookList", orderBooks);
        mav.addObject("book", new Book());
        return mav;
    }
    @GetMapping(value = "/userApproval")
    public ModelAndView userApproval(String role) {
        ModelAndView mav = new ModelAndView("userApprovalByLibrarian");
        List<User> userList = userService.getAllUsers(role);
        mav.addObject("userList", userList);
        mav.addObject("user", new User());
        return mav;
    }
    @GetMapping(value = "/editUserApprovalByLibrarian/{userId}")
    public ModelAndView editUserApprovalByLibrarian(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("editUserApprovalByAdmin");
        User user =  userService.getUserById(userId);
        mav.addObject("user", user);
        return mav;
    }
    @PostMapping(value = "/userApprovalUpdateByAdmin")
    public ModelAndView userApprovalUpdateByLibrarian(@ModelAttribute("user") UserDTO userDTO, @RequestParam int userId) {
         userService.approvalUpdateByLibrarian(userDTO,userId);
        return new ModelAndView("approvalSuccessByAdmin");
    }
   @GetMapping(value = "/editUserForStatusApproval/{orderId}")
    public ModelAndView editUserForStatusApproval(@PathVariable int orderId) {
    ModelAndView mav = new ModelAndView("confirmMessageForApprove");
    OrderBook orderBook =  orderBooksService.getOrderBook(orderId);
    mav.addObject("orderBook", orderBook);
    return mav;
}
    @GetMapping(value = "/editUserForStatusRejected/{orderId}")
    public ModelAndView   editUserForStatusRejected(@PathVariable int orderId) {
        ModelAndView mav = new ModelAndView("confirmMessageForReject");
        OrderBook orderBook =  orderBooksService.getOrderBook(orderId);
        mav.addObject("orderBook", orderBook);
        return mav;
    }

//    @PostMapping(value = "/userStatusApproveByAdmin")
//    public ModelAndView userStatusUpdateByAdmin(@ModelAttribute("orderBook") OrderBookDTO orderBookDTO, @RequestParam int orderId) {
//        orderBooksService.statusUpdateByLibrarian(orderBookDTO,orderId);
//        return new ModelAndView("approvalSuccessByAdmin");
//    }
//    @PostMapping(value = "/userStatusRejectByAdmin")
//    public ModelAndView userStatusRejectByAdmin(@ModelAttribute("orderBook") OrderBookDTO orderBookDTO, @RequestParam int orderId) {
//        orderBooksService.statusRejectByLibrarian(orderBookDTO,orderId);
//        return new ModelAndView("approvalStatusRejected");
//    }
}
