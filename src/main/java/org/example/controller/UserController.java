package org.example.controller;

import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.CartRepository;
import org.example.dto.AddressDTO;
import org.example.dto.UserDTO;
import org.example.model.*;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@RestController
public class UserController {
    private static final String ADDRESS = "addressDTO";
    private static final String USERID = "userId";
    private static final String BOOK_LIST = "bookList";
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    BooksService booksService;
    @Autowired
    CartService cartService;
    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CartRepository cartRepository;


    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }


    @RequestMapping(value = "/register")
    public ModelAndView register(Principal principal) {
        ModelAndView mav = new ModelAndView("registerForm");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping(value = "/saveUser")
    public ModelAndView saveUser(@ModelAttribute("user") UserDTO userDTO) {
        return userService.saveUser(userDTO);

    }

    @GetMapping(value = "/home")
    public ModelAndView home(ModelMap model, Principal principal) {
        return userService.validateAndReturnHome(principal);
    }

    @GetMapping(value = "/login")
    public ModelAndView login(ModelMap model) {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/loginError")
    public ModelAndView loginError(Principal principal) {
        ModelAndView model = new ModelAndView("login");
        model.addObject("error", "true");
        return model;
    }

    @GetMapping(value = "/403")
    public ModelAndView accessDenied(Principal user) {
        return userService.errorMessage(user);
    }

    @GetMapping(value = "/myProfile/{userId}")
    public ModelAndView myProfile(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("myProfile");
        User user = userService.getUserById(userId);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping(value = "/editProfile/{userId}")
    public ModelAndView editProfile(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("editProfile");
        User user = userService.getUserById(userId);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping(value = "/profileUpdate")
    public ModelAndView profileUpdate(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUserProfile(userDTO);
        return new ModelAndView("profileUpdatedSuccess");
    }

    @GetMapping(value = "/addAddress/{userId}")
    public ModelAndView addAddress(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("addressForm");
        mav.addObject(ADDRESS, new Address());
        return mav;
    }

    @PostMapping(value = "/saveAddress/{userId}")
    public ModelAndView saveAddress(@ModelAttribute("address") AddressDTO addressDTO, @ModelAttribute("user") UserDTO userDTO, @PathVariable int userId) {
        addressService.addAddress(addressDTO, userId, userDTO);
        return new ModelAndView("addressAddedSuccessfully");
    }

    @GetMapping(value = "/displayAddress/{userId}")
    public ModelAndView displayAddress(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("displayAddress");
        List<Address> addressList = addressService.displayAddressById(userId);
        mav.addObject(ADDRESS, addressList);
        return mav;
    }

    @GetMapping(value = "/editAddress")
    public ModelAndView editAddress(@RequestParam("userId") int userId, @RequestParam("addressId") int addressId) {
        ModelAndView mav = new ModelAndView("editAddress");
        User user = userService.getUserById(userId);
        Address address = addressService.findAddressById(addressId);
        mav.addObject(ADDRESS, address);
        mav.addObject(USERID, user);
        return mav;
    }

    @PostMapping(value = "/addressUpdate")
    public ModelAndView addressUpdate(@ModelAttribute("address") AddressDTO addressDTO) {
        addressService.addressUpdate(addressDTO);
        return new ModelAndView("addressUpdatedSuccessfully");
    }

    @GetMapping(value = "/book/{userId}")
    public ModelAndView bookList(@PathVariable int userId) {
        return booksService.displayCustomBooks(userId);
    }

    @GetMapping(value = "/addToCart")
    public ModelAndView orderBook(@RequestParam("bookId") int bookId, @RequestParam("userId") int userId) {
        ModelAndView mav = new ModelAndView("addToCart");
        CartDetail cartDetail = new CartDetail();
        mav.addObject("cartDetail", cartDetail);
        mav.addObject(USERID, userId);
        mav.addObject("bookId", bookId);
        return mav;
    }

    @PostMapping(value = "/saveCart/{bookId}/{userId}")
    public ModelAndView saveCart(@PathVariable int bookId, @PathVariable int userId, @ModelAttribute("cartDetail") CartDetail cartDetail) {
        Cart cart = cartService.getCartById(userId, bookId);
        cartDetail.setBookId(bookId);
        cartDetail.setCartId(cart.getCartId());
        cart.setRequestBook(false);
        cartDetailRepository.save(cartDetail);
        cartRepository.save(cart);
        return new ModelAndView("addToCartSuccess");
    }

    @GetMapping(value = "/displayCartBooks/{userId}")
    public ModelAndView displayCartBooks(@PathVariable int userId) {
        return booksService.displayCartsBooks(userId);
    }


    @PostMapping(value = "/saveOrderBook/{cartId}/{userId}")
    public ModelAndView saveOrderBook(@ModelAttribute("cart") Cart cart, @PathVariable("userId") int userId) {
        cartService.placeOrder(cart, userId);
        return new ModelAndView("orderPlacedSuccess");
    }

    @GetMapping(value = "/searchBook/{bookId}")
    public ModelAndView searchBook(@PathVariable int bookId) {
        return booksService.searchBook(bookId);
    }

    @GetMapping(value = "/viewBooks/{cartId}/{userId}")
    public ModelAndView viewBooks(@PathVariable int cartId, @PathVariable int userId) {
        return booksService.viewBooks(cartId, userId);
    }

    @GetMapping(value = "/loanedBooks/{userId}")
    public ModelAndView myOrder(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("myOrdersAndReturn");
        List<Cart> cartList = cartRepository.displayLoanedBookByCartId(userId, String.valueOf(orderStatus.APPROVED));
        mav.addObject("cartList", cartList);
        mav.addObject("book", new Book());
        return mav;
    }

    @GetMapping(value = "/pendingBooks/{userId}")
    public ModelAndView pendingBooks(@PathVariable int userId) {
        return booksService.displayPendingBooks(userId);
    }

    @PostMapping(value = "/returnBook/{cartId}/{userId}")
    public ModelAndView returnBook(@PathVariable("cartId") int cartId, @PathVariable("userId") int userId) {
        return booksService.returnBooks(cartId, userId);
    }
    @PostMapping("/searchByGenre")
    public ModelAndView searchByGenre(@RequestParam("genre") String genre) {
        List<Book> bookList = booksService.findByGenre(genre);
        ModelAndView mav = new ModelAndView("genreBooks");
        mav.addObject("book",bookList);
        mav.addObject(BOOK_LIST, bookList);
        return mav;
    }
}

