package com.elearning.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    private String title;
    private String videoUrl;
    private Integer orderIndex;
    private boolean isFreePreview;
}