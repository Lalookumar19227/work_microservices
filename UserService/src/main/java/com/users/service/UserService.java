package com.users.service;

import com.users.entities.Users;

import java.util.List;

public interface UserService {

    Users createUser(Users user);

    List<Users> getAllUsers();
//
//    Optional<User> getUserById(Long id);
//
//    User getUserByEmail(String email);
//
//    void deleteUserById(Long id);
//
//    String updateUser(Long id, User user);






}
