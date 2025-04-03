package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStudentDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
}
// No se incluye el RUT en este DTO porque no se puede modificar