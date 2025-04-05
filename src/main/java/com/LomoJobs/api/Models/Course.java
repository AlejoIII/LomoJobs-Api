package com.LomoJobs.api.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Level level = Level.Medio;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToOne(mappedBy = "course")
    private Professor professor;
}
