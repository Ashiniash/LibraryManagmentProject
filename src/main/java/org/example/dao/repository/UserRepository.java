package org.example.dao.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.username=:username ")
    List<User> registeredUsername(@Param("username") String username);

    @Query("SELECT user from User user WHERE user.username=:username")
    public User findByName(@Param("username") String username);
    @Query("SELECT user FROM User user WHERE user.role='ROLE_USER'")
    List<User> findAll(@Param("role") String role);
}
