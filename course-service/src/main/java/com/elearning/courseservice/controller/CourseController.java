package com.elearning.courseservice.controller;

import com.elearning.courseservice.dto.CourseRequest;
import com.elearning.courseservice.dto.CourseResponse;
import com.elearning.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }
}