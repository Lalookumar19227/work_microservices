package com.users.repo;

import com.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users getUserByEmail(String email);

}
