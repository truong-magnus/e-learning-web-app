package com.elearning.courseservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // One Category can have many Courses
    @JsonIgnore // Prevents infinite recursion during JSON serialization
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Course> courses;
}