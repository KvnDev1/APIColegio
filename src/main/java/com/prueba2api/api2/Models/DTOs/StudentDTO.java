package com.prueba2api.api2.Models.DTOs;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private UUID studentId;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$", 
             message = "El RUT debe tener formato XX.XXX.XXX-X")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    private LocalDateTime createdAt;

    //Se mostrara en que curso esta inscrito el estudiante
    private Set<UUID> courseIds;
    private Set<String> courseNames;
}
