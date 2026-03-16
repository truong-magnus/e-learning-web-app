package com.elearning.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {
    private Long id;
    private String title;
    private Integer orderIndex;
    private Long courseId;
}