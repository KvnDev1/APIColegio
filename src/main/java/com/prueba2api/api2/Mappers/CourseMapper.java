package com.prueba2api.api2.Mappers;

import com.prueba2api.api2.Models.Course;
import com.prueba2api.api2.Models.DTOs.CourseDTO;
import com.prueba2api.api2.Models.DTOs.CreateCourseDTO;
import com.prueba2api.api2.Models.DTOs.UpdateCourseDTO;
import com.prueba2api.api2.Models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    // Al convertir de Course a CourseDTO, extraemos el teacherId del objeto Teacher
    @Mapping(source = "teacher.teacherId", target = "teacherId")
    @Mapping(source = "teacher.teacherName", target = "teacherName")
    CourseDTO toDTO(Course course);
    
    // Al convertir de CourseDTO a Course, mapeamos el campo teacherId a un objeto Teacher
    @Mapping(source = "teacherId", target = "teacher")
    Course toEntity(CourseDTO courseDTO);

    // Convierte de CreateCourseDTO a Course
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(target = "courseId", ignore = true) 
    Course toEntity(CreateCourseDTO createCourseDTO);

    // Actualiza una entidad Course a partir de UpdateCourseDTO
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(target = "courseId", ignore = true)
    void updateCourseFromDTO(UpdateCourseDTO updateCourseDTO, @MappingTarget Course course);
    
    // Metodo auxiliar para mapear de UUID a Teacher
    // MapStruct lo va usar automaticamente
    default Teacher mapTeacherFromId(java.util.UUID teacherId) {
        if (teacherId == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        return teacher;
    }
}
