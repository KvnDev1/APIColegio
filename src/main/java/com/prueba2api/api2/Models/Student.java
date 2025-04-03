package com.prueba2api.api2.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID studentId;

    @Column(name = "rut" , nullable = false, unique = true)
    @NotBlank(message = "rut obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$", 
            message = "El rut debe tener formato XX.XXX.XXX-X") //Validacion para el rut
    private String rut;

    @NotBlank(message = "nombre obligatorio")// NotBlank verifica que no sea null, no este vacio y no contenga solo espacios
    private String name;

    @NotBlank(message = "apellido obliogatorio")
    private String lastName;

    @Column(name = "created_at", updatable = false) // Se guarda pero no se actualiza
    private LocalDateTime createdAt;

    // Se asigna la fecha de creación automáticamente antes de guardar
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
}}
