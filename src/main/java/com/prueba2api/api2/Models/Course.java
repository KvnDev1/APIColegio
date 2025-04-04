package com.prueba2api.api2.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del curso es obligatorio")
    private String courseName;

    @Column(length = 1000)
    @NotBlank(message = "La descripción del curso es obligatoria")
    private String description;

    // Relacion curso es impartido por un profesor
    @ManyToOne(optional = true)
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

}
