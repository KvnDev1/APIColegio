package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private UUID courseId;
    
    @NotBlank(message = "El nombre del curso es obligatorio")
    private String courseName;
    
    private String description;
    
    // Referencia al profesor que esta asignado en el curso
    private UUID teacherId;
    private String teacherName;
}
