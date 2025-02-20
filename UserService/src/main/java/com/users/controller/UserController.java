package com.users.controller;


import com.users.entities.Users;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public Users createUser(@RequestBody Users user){
      return  userService.createUser(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/getAll")
//    public List<User> getAll(){
//        return userService.getAllUsers();
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/getById/{id}")
//    public Optional<User> getById(@PathVariable("id") Long id){
//        return userService.getUserById(id);
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/getByEmail/{email}")
//    public User getByEmail(@PathVariable String email){
//        return userService.getUserByEmail(email);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteById(@PathVariable("id") Long id){
//        userService.deleteUserById(id);
//    }
//
//    @PutMapping("/update/{id}")
//    public String updateUser(@PathVariable Long id, @RequestBody User user){
//        userService.updateUser(id, user);
//        return "";
//    }

}
