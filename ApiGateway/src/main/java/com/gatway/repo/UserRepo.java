package com.gatway.repo;

import com.gatway.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users getUserByEmail(String email);

}
