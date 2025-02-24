package com.FullFledgedEcommerce.JWT;

//import com.FullFledgedEcommerce.entites.ForUserDetails;
//import com.FullFledgedEcommerce.entites.User;
//import com.FullFledgedEcommerce.repo.UserRepo;
import com.users.UserRepo.UserRepo;
import com.users.entities.Users;
import com.users.jwt.ForUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userByEmail = userRepo.findByEmail(username);
        return new ForUserDetails(userByEmail.orElse(null));
    }
}
