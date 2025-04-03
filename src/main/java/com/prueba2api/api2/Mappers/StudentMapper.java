package com.prueba2api.api2.Mappers;

import org.mapstruct.Mapper;

import com.prueba2api.api2.Models.Student;
import com.prueba2api.api2.Models.DTOs.StudentDTO;

@Mapper(componentModel = "spring") // Indica que este mapper será un componente de Spring
// y se generará automáticamente en tiempo de compilación
public interface StudentMapper {

    StudentDTO toDTO(Student student); // Convierte la entidad Student a StudentDTO
    Student toEntity(StudentDTO studentDTO); // Convierte el DTO StudentDTO a la entidad Student
    
}
// El mapper hace la conversion entre la entidad Student y el DTO StudentDTO