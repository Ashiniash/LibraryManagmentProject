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
    OrderBooksService orderBooksService;
    @Autowired
    CartService cartService;
    @Autowired
    CartDetailService cartDetailService;
    @Autowired
    CartDetailRepository cartDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BooksRepository booksRepository;

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
        cartDetail.setOrderStatus(String.valueOf(orderStatus.PENDING));
        cartDetail.setUserId(userId);
        cartDetailRepository.save(cartDetail);
        return new ModelAndView("addToCartSuccess");
    }

    @GetMapping(value = "/displayCartBooks/{userId}")
    public ModelAndView displayBasketSongs(@PathVariable int userId) {
        try {
            Cart cart = cartService.getCartByStatus(userId, String.valueOf(orderStatus.PENDING));
            Cart cartObj = new Cart();
            if (cart == null) {
                cartObj = newCart(userId);
            }
            assert cart != null;
            List<CartDetail> cartDetailList = cartDetailRepository.findCart(userId, cart.getCartId());
            List<Book> bookList = new ArrayList<>();
            for (CartDetail cartDetail : cartDetailList) {
                Book book = booksService.findById(cartDetail.getBookId());
                bookList.add(book);
            }
            ModelAndView modelAndView = new ModelAndView("displayCartBooks");
            modelAndView.addObject("bookList", bookList);
            modelAndView.addObject("cartId", cart.getCartId());
            return modelAndView;
        } catch (NullPointerException e) {
            return new ModelAndView("nullCart");
        }
    }

    private Cart newCart(int userId) {
        Cart cart = new Cart();
        cart.setOrderStatus(String.valueOf(orderStatus.PENDING));
        cart.setUserId(userId);
        return cart;
    }

    @RequestMapping(value = "/saveOrderBook/{cartId}")
    public ModelAndView saveOrderBook(@PathVariable int cartId) {
        Cart cart = cartService.findCartById(cartId);
        User user = userService.getUserById(cart.getUserId());
        List<CartDetail> cartDetailList = cartDetailRepository.findCart(user.getUserId(), cartId);
        Cart carts = cartService.getCartBookById(cartId);
        OrderBook orderBook = null;
        for (CartDetail cartDetail : cartDetailList) {
            Book book = booksService.getBookById(cartDetail.getBookId());
            orderBook = new OrderBook();
            orderBook.setUserId(user.getUserId());
        }
        return orderBooksService.insertOrderBook(cartId);
    }
//    @RequestMapping(value = "/displayRequestSongs/{userId}")
//    public ModelAndView displayRequestSongs(@PathVariable int userId, String orderStatus) {
//        List<OrderBook> orderBookList = booksService.find1(userId, status);
//        List<Content> contentList1 = new ArrayList<>();
//        for (PurchasedSongs purchasedSongs : contentList) {
//            contentList1 = contentRepository.getBasketSongById(purchasedSongs.getBasketId());
//        }
//        List<Songs> songsList = new ArrayList<>();
//        for (Content content : contentList1) {
//            Songs songs = songsRepository.findById(content.getSongId()).get();
//            songsList.add(songs);
//        }
//        ModelAndView mav = new ModelAndView("displayRequestedSongs");
//        mav.addObject("songsList", songsList);
//        mav.addObject("purchasedId", purchasedSongsService.getPurchasedSongById(userId, status));
//        return mav;
//    }

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
}

