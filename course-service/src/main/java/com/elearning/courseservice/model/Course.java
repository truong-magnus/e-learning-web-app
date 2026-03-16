package com.elearning.courseservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;

    // MICROSERVICES BEST PRACTICE: Store instructor ID, do not map to User entity
    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;

    private String status; // e.g., DRAFT, PUBLISHED

    // Many Courses belong to one Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // One Course has many Sections
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;
}