package com.elearning.courseservice.controller;

import com.elearning.courseservice.dto.SectionRequest;
import com.elearning.courseservice.dto.SectionResponse;
import com.elearning.courseservice.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/{courseId}/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SectionResponse createSection(
            @PathVariable Long courseId,
            @RequestBody SectionRequest request) {
        return sectionService.createSection(courseId, request);
    }
}