package com.elearning.userservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    // BCrypt is used to hash passwords before saving to DB
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 1. REGISTER API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
        }

        // Encrypt the password and save to DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // 2. LOGIN & GET TOKEN API
    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AppUser userRequest) {
        Optional<AppUser> existingUser = userRepository.findByUsername(userRequest.getUsername());

        // Verify username and check if raw password matches the hashed password in DB
        if (existingUser.isPresent() && passwordEncoder.matches(userRequest.getPassword(), existingUser.get().getPassword())) {
            // Generate and return JWT Token
            String token = jwtService.generateToken(userRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}