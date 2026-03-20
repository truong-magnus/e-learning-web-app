package com.elearning.courseservice.service;

import com.elearning.courseservice.client.UserClient;
import com.elearning.courseservice.dto.CourseRequest;
import com.elearning.courseservice.dto.CourseResponse;
import com.elearning.courseservice.dto.UserDto;
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

    // Inject the OpenFeign Client
    private final UserClient userClient;

    public CourseResponse createCourse(CourseRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setInstructorId(request.getInstructorId());
        course.setStatus("DRAFT");
        course.setCategory(category);

        Course savedCourse = courseRepository.save(course);
        log.info("Course {} saved successfully", savedCourse.getId());

        return mapToCourseResponse(savedCourse);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::mapToCourseResponse)
                .toList();
    }

    private CourseResponse mapToCourseResponse(Course course) {
        // Inter-Service Communication: Call user-service via OpenFeign
        String instructorFullName = "Unknown Instructor";
        try {
            UserDto userDto = userClient.getUserById(course.getInstructorId());
            if (userDto != null) {
                instructorFullName = userDto.getFirstName() + " " + userDto.getLastName();
            }
        } catch (Exception e) {
            // If user-service is down or user not found, we log the error
            // but don't break the whole Course API (Resilience)
            log.error("Failed to fetch instructor details for ID {}: {}", course.getInstructorId(), e.getMessage());
        }

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                instructorFullName, // Mapped the fetched name here!
                course.getStatus(),
                course.getCategory() != null ? course.getCategory().getName() : "Uncategorized"
        );
    }
}