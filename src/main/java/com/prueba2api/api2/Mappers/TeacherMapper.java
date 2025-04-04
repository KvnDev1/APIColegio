package com.prueba2api.api2.Mappers;

import com.prueba2api.api2.Models.Teacher;
import com.prueba2api.api2.Models.DTOs.TeacherDTO;
import com.prueba2api.api2.Models.DTOs.CreateTeacherDTO;
import com.prueba2api.api2.Models.DTOs.UpdateTeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    // Convierte Teacher a TeacherDTO, mapeando la colección 'courses' a courseIds y courseNames
    @Mapping(
        target = "courseIds",
        expression = "java( teacher.getCourses() == null ? null : teacher.getCourses().stream().map(course -> course.getCourseId()).collect(java.util.stream.Collectors.toSet()) )"
    )
    @Mapping(
        target = "courseNames",
        expression = "java( teacher.getCourses() == null ? null : teacher.getCourses().stream().map(course -> course.getCourseName()).collect(java.util.stream.Collectors.toSet()) )"
    )
    TeacherDTO toDTO(Teacher teacher);

    // Convierte TeacherDTO a Teacher, ignorando la propiedad courses, ya que se gestionará por separado
    @Mapping(target = "courses", ignore = true)
    Teacher toEntity(TeacherDTO teacherDTO);

    // Convierte CreateTeacherDTO a Teacher, ignorando courses y teacherId (generado por la base de datos)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "teacherId", ignore = true)
    Teacher toEntity(CreateTeacherDTO createTeacherDTO);

    // Actualiza una entidad Teacher a partir de UpdateTeacherDTO, ignorando la propiedad courses
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "teacherId", ignore = true)
    @Mapping(target = "rut", ignore = true)
    void updateTeacherFromDTO(UpdateTeacherDTO updateTeacherDTO, @MappingTarget Teacher teacher);
}
