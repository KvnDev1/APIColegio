package com.prueba2api.api2.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;



@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Siempre se asignará Role.ADMIN en el registro de un nuevo usuario
}
