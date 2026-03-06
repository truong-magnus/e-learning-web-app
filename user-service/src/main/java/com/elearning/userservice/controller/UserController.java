package com.elearning.userservice.controller;

import com.elearning.userservice.client.CourseClient;
import com.elearning.userservice.dto.CourseResponse;
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
    public List<CourseResponse> getCoursesFromCourseService() {
        // This method magically calls the Course Service via OpenFeign
        return courseClient.getAllCourses();
    }
}