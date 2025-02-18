package com.gatway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String generateToken(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
