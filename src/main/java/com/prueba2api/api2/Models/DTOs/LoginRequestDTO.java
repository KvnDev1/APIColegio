package com.prueba2api.api2.Models.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "El email es obligatorio")
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
