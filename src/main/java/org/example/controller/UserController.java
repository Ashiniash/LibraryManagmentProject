package org.example.controller;

import org.example.dao.repository.BooksRepository;
import org.example.dao.repository.CartDetailRepository;
import org.example.dao.repository.CartRepository;
import org.example.dao.repository.UserRepository;
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

    enum orderStatus {
        PENDING,
        APPROVED,
        RETURNED;
    }

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
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BooksRepository booksRepository;

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
    public ModelAndView saveAddress(@ModelAttribute("address") AddressDTO address, @ModelAttribute("user") UserDTO user, @PathVariable int userId) {
        addressService.addAddress(address, userId, user);
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
    public ModelAndView addressUpdate(@ModelAttribute("address") AddressDTO address) {
        addressService.addressUpdate(address);
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
    public ModelAndView displayBasketSongs(@PathVariable int userId) {
        try {
            Cart cart = cartService.getCartByStatus(userId, String.valueOf(orderStatus.PENDING));
            List<Cart> cartList = cartRepository.displayMyOrdersByStatus(userId,String.valueOf(orderStatus.PENDING));
            List<Cart> newCartList = new ArrayList<>();
            List<Book> newBookList = new ArrayList<>();
            for (Cart carts : cartList) {
                newCartList.add(cartRepository.findCartsById(carts.getCartId(), carts.getUserId()));
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(carts.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = booksService.findById(cartDetails.getBookId());
                    newBookList.add(book);
                }
            }
            ModelAndView modelAndView = new ModelAndView("displayCartBooks");
            modelAndView.addObject(BOOK_LIST, newBookList);
            modelAndView.addObject("cart",newCartList);
            modelAndView.addObject("cartId", cart.getCartId());
            return modelAndView;
        } catch (NullPointerException e) {
            return new ModelAndView("nullCart");
        }
    }


    @PostMapping(value = "/saveOrderBook/{cartId}/{userId}")
    public ModelAndView saveOrderBook(@ModelAttribute("cart") Cart cart, @PathVariable("userId") int userId) {
        cartService.placeOrder(cart, userId);
        return new ModelAndView("orderPlacedSuccess");
    }


    @GetMapping(value = "/searchBook/{bookId}")
    public ModelAndView searchBook(@PathVariable int bookId) {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("searchedBookId", bookId);
            Book book = booksService.findById(bookId);
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

    @GetMapping(value = "/myOrder/{userId}")
    public ModelAndView myOrder(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("myOrders");
        List<Cart> newCartList = new ArrayList<>();
        List<Book> newBookList = new ArrayList<>();
        List<Cart> cartList = cartRepository.displayMyOrders(userId);
        for (Cart cart : cartList) {
            if (cart.getOrderStatus().equalsIgnoreCase("approved")) {
                newCartList.add(cartRepository.findCartById(cart.getCartId(), cart.getUserId()));
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(cart.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = booksService.findById(cartDetails.getBookId());
                    newBookList.add(book);
                }
            }
        }
        mav.addObject(BOOK_LIST, newBookList);
        mav.addObject("cart",newCartList);
        mav.addObject("book", new Book());
        return mav;
    }


    @GetMapping(value = "/pendingBooks/{userId}")
    public ModelAndView pendingBooks(@PathVariable int userId) {
        ModelAndView mav = new ModelAndView("myOrders");
        List<Cart> newCartList = new ArrayList<>();
        List<Book> newBookList = new ArrayList<>();
        List<Cart> cartList = cartRepository.displayMyOrders(userId);
        for (Cart cart : cartList) {
            if (cart.getOrderStatus().equalsIgnoreCase("pending")) {
                newCartList.add(cartRepository.findCartById(cart.getCartId(), cart.getUserId()));
                List<CartDetail> cartDetail = cartDetailRepository.findByCartId(cart.getCartId());
                for (CartDetail cartDetails : cartDetail) {
                    Book book = booksService.findById(cartDetails.getBookId());
                    newBookList.add(book);
                    mav.addObject(BOOK_LIST, newBookList);
                    mav.addObject("cart",newCartList);
                }
            }
        }
        mav.addObject("book", new Book());
        return mav;
    }


}

