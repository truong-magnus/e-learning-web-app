package com.elearning.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String title;
    private String description;
    private double price;
    private Long instructorId; // ID of the user creating the course
    private Long categoryId;   // ID of the category this course belongs to
}