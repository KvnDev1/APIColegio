package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseDTO {

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String courseName;

    // Descripcion opcional por ahora
    private String description;

    // El ID del profesor es necesario para asignar el curso
    private UUID teacherId;
}
