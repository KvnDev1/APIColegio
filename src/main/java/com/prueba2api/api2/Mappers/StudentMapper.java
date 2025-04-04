package com.prueba2api.api2.Mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba2api.api2.Models.Student;
import com.prueba2api.api2.Models.DTOs.StudentDTO;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Se utiliza para convertir la entidad Student a StudentDTO
    @Mapping(target = "courseIds", ignore = true)
    @Mapping(target = "courseNames", ignore = true)
    StudentDTO toDTO(Student student);

    // Se utiliza para convertir el DTO StudentDTO a la entidad Student
    Student toEntity(StudentDTO studentDTO);
}