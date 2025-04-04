package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private UUID teacherId;
    
    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(
        regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$",
        message = "El RUT debe tener formato XX.XXX.XXX-X"
    )
    private String rut;
    
    @NotBlank(message = "El nombre completo es obligatorio")
    private String teacherName;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    // Se mostrara que cursos imparte el profesor
    private Set<UUID> courseIds;
    private Set<String> courseNames;
}
