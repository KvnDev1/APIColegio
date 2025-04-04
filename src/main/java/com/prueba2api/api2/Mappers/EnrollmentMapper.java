package com.prueba2api.api2.Mappers;

import com.prueba2api.api2.Models.Enrollment;
import com.prueba2api.api2.Models.DTOs.EnrollmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    // Mapea Enrollment a EnrollmentDTO extrayendo los IDs de student y course
    @Mapping(source = "student.studentId", target = "studentId")
    @Mapping(source = "course.courseId", target = "courseId")
    EnrollmentDTO toDTO(Enrollment enrollment);

    // Mapea EnrollmentDTO a Enrollment
    // Ignoramos la asociación directa ,se manejara en el servicio
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentDTO enrollmentDTO);
}
