package com.users.controller;


import com.users.UserRepo.UserRepo;
import com.users.entities.Users;
import com.users.jwt.JwtService;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/create")
    public Users createUser(@RequestBody Users user){
        return  userService.createUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Users user) {
        String email = user.getEmail();
        String password = user.getPassword();

        // Fetch user from DB
        Optional<Users> user1 = userRepo.findByEmail(email);

        // Check if user exists
        if (user1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        // Authenticate the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Generate JWT token
        String role = user1.get().getRole().name();
        String token = jwtService.generateToken(email, role);

        return ResponseEntity.ok(token);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<Users> getAll(){
        return userService.getAllUsers();








    }









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
