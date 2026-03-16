package com.elearning.courseservice.service;

import com.elearning.courseservice.dto.SectionRequest;
import com.elearning.courseservice.dto.SectionResponse;
import com.elearning.courseservice.model.Course;
import com.elearning.courseservice.model.Section;
import com.elearning.courseservice.repository.CourseRepository;
import com.elearning.courseservice.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;

    public SectionResponse createSection(Long courseId, SectionRequest request) {
        // 1. Find the parent Course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with ID " + courseId + " not found"));

        // 2. Map DTO to Entity
        Section section = new Section();
        section.setTitle(request.getTitle());
        section.setOrderIndex(request.getOrderIndex());
        section.setCourse(course); // Link the section to the course

        // 3. Save to database
        Section savedSection = sectionRepository.save(section);
        log.info("Section {} is saved successfully for Course {}", savedSection.getId(), courseId);

        // 4. Return the Response DTO
        return new SectionResponse(
                savedSection.getId(),
                savedSection.getTitle(),
                savedSection.getOrderIndex(),
                savedSection.getCourse().getId()
        );
    }
}