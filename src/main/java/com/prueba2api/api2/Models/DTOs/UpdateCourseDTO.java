package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDTO {

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String courseName;

    private String description;

    // Opcional si se desea cambiar el profesor asignado
    private UUID teacherId;
}
