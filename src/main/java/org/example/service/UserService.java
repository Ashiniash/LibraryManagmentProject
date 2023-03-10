package org.example.service;

import org.example.dto.UserDTO;
import org.example.model.User;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> registeredUsername(String username);

    void registerUser(UserDTO user);

    User getUserByUserName(String loggedInUserName);

    User getUserById(int userId);

    ModelAndView saveUser(UserDTO userDTO);

    ModelAndView validateAndReturnHome(Principal principal);

    ModelAndView errorMessage(Principal user);

    void updateUserProfile(UserDTO userDTO);

    List<User> getAllUsers(String role);

    void approvalUpdateByLibrarian(UserDTO userDTO, int userId);


}
