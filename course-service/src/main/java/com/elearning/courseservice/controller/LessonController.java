package com.elearning.courseservice.controller;

import com.elearning.courseservice.dto.LessonRequest;
import com.elearning.courseservice.dto.LessonResponse;
import com.elearning.courseservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/{courseId}/sections/{sectionId}/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponse createLesson(
            @PathVariable Long courseId,
            @PathVariable Long sectionId,
            @RequestBody LessonRequest request) {

        // We pass sectionId to the service to link the lesson
        return lessonService.createLesson(sectionId, request);
    }
}