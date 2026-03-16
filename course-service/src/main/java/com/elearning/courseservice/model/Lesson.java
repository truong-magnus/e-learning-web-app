package com.elearning.courseservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoUrl;
    private Integer orderIndex; // The order of the lesson within the section

    @Column(name = "is_free_preview")
    private boolean isFreePreview; // Is this lesson available for free preview?

    // Many Lessons belong to one Section
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}