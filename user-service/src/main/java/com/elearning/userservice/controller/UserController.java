package com.elearning.userservice.controller;

import com.elearning.userservice.client.CourseClient;
import com.elearning.userservice.dto.CourseResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CourseClient courseClient;

    @GetMapping("/test-courses")
    @CircuitBreaker(name = "courseService", fallbackMethod = "courseFallback")
    public List<CourseResponse> getCoursesFromCourseService() {
        return courseClient.getAllCourses();
    }

    // Fallback method invoked when Course Service is down or failing
    public List<CourseResponse> courseFallback(Throwable e) {
        CourseResponse fallbackCourse = new CourseResponse(
                0L,
                "Course System Maintenance",
                "The course service is temporarily unavailable. Please try again later.",
                0.0
        );
        return List.of(fallbackCourse);
    }
}