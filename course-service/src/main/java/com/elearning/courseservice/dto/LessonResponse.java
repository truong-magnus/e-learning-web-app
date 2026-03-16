package com.elearning.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String title;
    private String videoUrl;
    private Integer orderIndex;
    private boolean isFreePreview;
    private Long sectionId;
}