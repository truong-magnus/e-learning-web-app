package com.elearning.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private double price;
    private Long instructorId;
    private String status;
    private String categoryName; // Only return the category name, not the whole Category object
}