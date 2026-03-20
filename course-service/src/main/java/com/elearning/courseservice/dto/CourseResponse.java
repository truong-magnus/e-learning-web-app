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

    // Changed from instructorId to instructorName to show real data
    private String instructorName;

    private String status;
    private String categoryName;
}