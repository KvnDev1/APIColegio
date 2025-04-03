package com.prueba2api.api2.Service;

import com.prueba2api.api2.Models.Course;
import com.prueba2api.api2.Models.DTOs.CourseDTO;
import com.prueba2api.api2.Mappers.CourseMapper;
import com.prueba2api.api2.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // Obtiene todos los cursos y los mapea a DTO
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Busca un curso por UUID y lo convierte a DTO
    public Optional<CourseDTO> getCourseById(UUID courseId) {
        return courseRepository.findById(courseId)
                .map(courseMapper::toDTO);
    }

    // Guarda o actualiza un curso a partir de CourseDTO y retorna el objeto guardado en forma de DTO
    public CourseDTO saveOrUpdate(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    // Elimina un curso por UUID
    public void delete(UUID courseId) {
        courseRepository.deleteById(courseId);
    }
}
