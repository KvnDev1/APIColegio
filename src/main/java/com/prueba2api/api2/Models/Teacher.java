package com.prueba2api.api2.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID teacherId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$", 
            message = "El RUT debe tener formato XX.XXX.XXX-X") // Validación para el RUT
    private String rut;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String teacherName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    // Relación: un profesor imparte varios cursos
    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Course> courses;
}
