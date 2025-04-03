package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
