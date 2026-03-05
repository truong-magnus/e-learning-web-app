package com.elearning.courseservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity (Database table)
@Table(name = "courses") // Specifies the table name in the database
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    private Long id;

    private String title;
    private String description;
    private double price;
}