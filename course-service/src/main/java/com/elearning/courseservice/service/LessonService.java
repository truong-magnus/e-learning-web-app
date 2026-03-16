package com.elearning.courseservice.service;

import com.elearning.courseservice.dto.LessonRequest;
import com.elearning.courseservice.dto.LessonResponse;
import com.elearning.courseservice.model.Lesson;
import com.elearning.courseservice.model.Section;
import com.elearning.courseservice.repository.LessonRepository;
import com.elearning.courseservice.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SectionRepository sectionRepository;

    public LessonResponse createLesson(Long sectionId, LessonRequest request) {
        // 1. Find the parent Section
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section with ID " + sectionId + " not found"));

        // 2. Map DTO to Entity
        Lesson lesson = new Lesson();
        lesson.setTitle(request.getTitle());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setOrderIndex(request.getOrderIndex());
        lesson.setFreePreview(request.isFreePreview());
        lesson.setSection(section); // Link lesson to the section

        // 3. Save to database
        Lesson savedLesson = lessonRepository.save(lesson);
        log.info("Lesson {} is saved successfully for Section {}", savedLesson.getId(), sectionId);

        // 4. Return the Response DTO (FIXED: Changed savedSection to savedLesson)
        return new LessonResponse(
                savedLesson.getId(),
                savedLesson.getTitle(),
                savedLesson.getVideoUrl(),
                savedLesson.getOrderIndex(),
                savedLesson.isFreePreview(),
                savedLesson.getSection().getId()
        );
    }
}