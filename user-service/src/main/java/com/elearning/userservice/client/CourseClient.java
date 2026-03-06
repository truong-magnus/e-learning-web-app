package com.elearning.userservice.client;

import com.elearning.userservice.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// The "name" must match EXACTLY the application.name of the Course Service in Eureka
@FeignClient(name = "course-service")
public interface CourseClient {

    // The endpoint must match EXACTLY the URL in Course Service
    @GetMapping("/api/courses")
    List<CourseResponse> getAllCourses();
}