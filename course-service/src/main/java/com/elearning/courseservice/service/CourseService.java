package com.elearning.courseservice.service;

import com.elearning.courseservice.dto.CourseRequest;
import com.elearning.courseservice.dto.CourseResponse;
import com.elearning.courseservice.model.Category;
import com.elearning.courseservice.model.Course;
import com.elearning.courseservice.repository.CategoryRepository;
import com.elearning.courseservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    // Create a new course
    public CourseResponse createCourse(CourseRequest request) {
        // 1. Find the category or throw an exception if not found
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category with ID " + request.getCategoryId() + " not found"));

        // 2. Map DTO to Entity
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setInstructorId(request.getInstructorId());
        course.setStatus("DRAFT"); // Default status for new courses
        course.setCategory(category);

        // 3. Save to database
        Course savedCourse = courseRepository.save(course);
        log.info("Course {} is saved successfully", savedCourse.getId());

        // 4. Return the Response DTO
        return mapToCourseResponse(savedCourse);
    }

    // Get all courses
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(this::mapToCourseResponse)
                .toList();
    }

    // Helper method to map Entity to Response DTO
    private CourseResponse mapToCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getInstructorId(),
                course.getStatus(),
                course.getCategory() != null ? course.getCategory().getName() : "Uncategorized"
        );
    }
}