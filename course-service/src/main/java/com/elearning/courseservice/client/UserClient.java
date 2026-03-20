package com.elearning.courseservice.client;

import com.elearning.courseservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// The name must exactly match the registered application name in Eureka Server
@FeignClient(name = "user-service")
public interface UserClient {

    // Declare the exact endpoint exposed by user-service
    @GetMapping("/api/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}