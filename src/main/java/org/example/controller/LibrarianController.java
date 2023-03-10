package org.example.controller;

import org.example.dto.BookDTO;
import org.example.dto.CartDTO;
import org.example.dto.UserDTO;
import org.example.model.Book;
import org.example.model.Cart;
import org.example.model.CartDetail;
import org.example.model.User;
import org.example.service.BooksService;
import org.example.service.CartService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    private static final String BOOK_LIST = "bookList";
    @Autowired
    BooksService booksService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/addBook")
    public ModelAndView addBook() {
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
        mav.addObject(BOOK_LIST, bookList);
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
        User user = userService.getUserById(userId);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping(value = "/userApprovalUpdateByAdmin")
    public ModelAndView userApprovalUpdateByLibrarian(@ModelAttribute("user") UserDTO userDTO, @RequestParam int userId) {
        userService.approvalUpdateByLibrarian(userDTO, userId);
        return new ModelAndView("approvalSuccessByAdmin");
    }

    @GetMapping(value = "/editUserForStatusApproval/{cartId}")
    public ModelAndView editUserForStatusApproval(@PathVariable int cartId) {
        ModelAndView mav = new ModelAndView("confirmMessageForApprove");
        Cart cart = cartService.getCartsById(cartId);
        mav.addObject("cart", cart);
        return mav;
    }

    @GetMapping(value = "/editUserForStatusRejected/{cartId}")
    public ModelAndView editUserForStatusRejected(@PathVariable int cartId) {
        ModelAndView mav = new ModelAndView("confirmMessageForReject");
        Cart cart = cartService.getCartsById(cartId);
        mav.addObject("cart", cart);
        return mav;
    }

    @PostMapping(value = "/userStatusApproveByAdmin")
    public ModelAndView userStatusUpdateByAdmin(@ModelAttribute("cart") CartDTO cartDTO, @RequestParam int cartId, @ModelAttribute("cartDetail") CartDetail cartDetail) {
        cartService.statusUpdateByLibrarian(cartDTO, cartId, cartDetail);
        return new ModelAndView("approvalSuccessByAdmin");
    }

    @PostMapping(value = "/userStatusRejectByAdmin")
    public ModelAndView userStatusRejectByAdmin(@ModelAttribute("cart") CartDTO cartDTO, @RequestParam int cartId) {
        cartService.statusRejectByLibrarian(cartDTO, cartId);
        return new ModelAndView("approvalStatusRejected");
    }

    @GetMapping(value = "/displayAllBooksByStatus")
    public ModelAndView displayAllBooksByStatus() {
        ModelAndView mav = new ModelAndView("displayAllBooksByStatus");
        List<Cart> cartList = cartService.getAllBookByStatus();
        mav.addObject("cartList", cartList);
        mav.addObject("book", new Book());
        return mav;
    }

    @GetMapping(value = "/viewBooks/{cartId}/{userId}")
    public ModelAndView viewBooks(@PathVariable int cartId, @PathVariable int userId) {
        return booksService.viewBooks(cartId, userId);
    }
}
