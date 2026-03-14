package com.elearning.userservice.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    // Find user by username for login verification
    Optional<AppUser> findByUsername(String username);
}