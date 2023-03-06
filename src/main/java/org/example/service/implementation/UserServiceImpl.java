package org.example.service.implementation;

import org.example.dao.repository.UserRepository;
import org.example.dto.UserDTO;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    private static final String ROLE_USER = "ROLE_USER";

    @Override
    public List<User> registeredUsername(String username) {
        return userRepository.registeredUsername(username);
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setConfirmPassword(userDTO.getConfirmPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserId(userDTO.getUserId());
        user.setActive(false);
        user.setRole(ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String loggedInUserName) {
        return userRepository.findByName(loggedInUserName);
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public ModelAndView saveUser(UserDTO userDTO) {
        List<User> usersList = registeredUsername(userDTO.getUsername());
        if (usersList.isEmpty() && (userDTO.getPassword().equals(userDTO.getConfirmPassword()))) {
            registerUser(userDTO);
            return new ModelAndView("registerSuccess");
        } else if (!usersList.isEmpty()) {
            return new ModelAndView("alreadyRegistered");
        } else {
            return new ModelAndView("incorrectPasswordError");
        }
    }

    @Override
    public ModelAndView validateAndReturnHome(Principal principal) {
        User user = getUserByUserName(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (user.getRole().equals("ROLE_LIBRARIAN")) {
            modelAndView.setViewName("librarianHome");
        } else {
            modelAndView.setViewName("userHome");
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @Override
    public ModelAndView errorMessage(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName() + ", You can not access this page!");
        } else {
            model.addObject("msg", "You can not access this page!");
        }
        model.setViewName("403");
        return model;
    }

    @Override
    public void updateUserProfile(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setConfirmPassword(userDTO.getConfirmPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserId(userDTO.getUserId());
        user.setActive(true);
        user.setRole(ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(String role) {
        return userRepository.findAll(role);
    }

    @Override
    public void approvalUpdateByLibrarian(UserDTO userDTO,int userId) {
        User userObj = userRepository.findById(userId).get();
        userObj.setRole(ROLE_USER);
        userObj.setActive(userDTO.getActive());
        userRepository.save(userObj);
    }

}
