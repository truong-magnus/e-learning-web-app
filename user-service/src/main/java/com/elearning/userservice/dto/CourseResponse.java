package com.elearning.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    // These fields must match the JSON keys returned by Course Service
    private Long id;
    private String title;
    private String description;
    private double price;
}