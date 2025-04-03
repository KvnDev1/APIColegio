package com.prueba2api.api2.Mappers;

import com.prueba2api.api2.Models.Teacher;
import com.prueba2api.api2.Models.DTOs.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toDTO(Teacher teacher); //Convierte la entidad Teacher a TeacherDTO
    Teacher toEntity(TeacherDTO teacherDTO); //Convierte el DTO TeacherDTO a la entidad Teacher
    
}
