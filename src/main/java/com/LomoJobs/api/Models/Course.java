package com.LomoJobs.api.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Level level = Level.Medio;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToOne(mappedBy = "course")
    private Professor professor;
}
