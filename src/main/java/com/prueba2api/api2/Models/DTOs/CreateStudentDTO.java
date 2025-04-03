package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateStudentDTO {

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$", 
            message = "El RUT debe tener formato XX.XXX.XXX-X")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
}
