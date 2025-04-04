package com.prueba2api.api2.Service;

import com.prueba2api.api2.Models.Enrollment;
import com.prueba2api.api2.Models.DTOs.EnrollmentDTO;
import com.prueba2api.api2.Mappers.EnrollmentMapper;
import com.prueba2api.api2.Repository.EnrollmentRepository;
import com.prueba2api.api2.Repository.StudentRepository;
import com.prueba2api.api2.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper,
                             StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Inscribir a un estudiante en un curso
    public EnrollmentDTO enrollStudent(UUID studentId, UUID courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado")));
        enrollment.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(savedEnrollment);
    }

    // Obtener todas las inscripciones de un estudiante
    public List<EnrollmentDTO> getEnrollmentsByStudent(UUID studentId) {
        return enrollmentRepository.findByStudentStudentId(studentId)
                .stream()
                .map(enrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
