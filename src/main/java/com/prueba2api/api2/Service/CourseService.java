package com.prueba2api.api2.Service;

import com.prueba2api.api2.Models.Course;
import com.prueba2api.api2.Models.DTOs.CourseDTO;
import com.prueba2api.api2.Models.DTOs.CreateCourseDTO;
import com.prueba2api.api2.Models.DTOs.UpdateCourseDTO;
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

    // Crea un curso a partir de CreateCourseDTO y devuelve el objeto guardado como DTO
    public CourseDTO createCourse(CreateCourseDTO createCourseDTO) {
        Course course = courseMapper.toEntity(createCourseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    // Actualiza un curso a partir de UpdateCourseDTO y devuelve el objeto actualizado como DTO
    public Optional<CourseDTO> updateCourse(UUID courseId, UpdateCourseDTO updateCourseDTO) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course courseToUpdate = courseOpt.get();
            courseMapper.updateCourseFromDTO(updateCourseDTO, courseToUpdate);
            Course updatedCourse = courseRepository.save(courseToUpdate);
            return Optional.of(courseMapper.toDTO(updatedCourse));
        }
        return Optional.empty();
    }

    // Elimina un curso por UUID
    public void delete(UUID courseId) {
        courseRepository.deleteById(courseId);
    }
}
