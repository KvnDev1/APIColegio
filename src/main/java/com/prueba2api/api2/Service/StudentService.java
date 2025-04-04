package com.prueba2api.api2.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prueba2api.api2.Models.Student;
import com.prueba2api.api2.Models.DTOs.StudentDTO;
import com.prueba2api.api2.Mappers.StudentMapper;
import com.prueba2api.api2.Repository.StudentRepository;
import com.prueba2api.api2.Repository.EnrollmentRepository; 

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentService enrollmentService;  

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
                          EnrollmentRepository enrollmentRepository, EnrollmentService enrollmentService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentService = enrollmentService;
    }
    // Metodo para obtener todos los estudiantes y convertirlos a DTO
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> { 
                    StudentDTO dto = studentMapper.toDTO(student);
                    // Consultar inscripciones y asignar courseIds y courseNames
                    dto.setCourseIds(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseId())
                        .collect(Collectors.toSet()));
                    dto.setCourseNames(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseName())
                        .collect(Collectors.toSet()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Método para obtener un estudiante por su UUID y convertirlo a DTO
    public Optional<StudentDTO> getStudent(UUID id) {
        return studentRepository.findById(id)
                .map(student -> {
                    StudentDTO dto = studentMapper.toDTO(student);
                    // Consultar inscripciones para este estudiante
                    dto.setCourseIds(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseId())
                        .collect(Collectors.toSet()));
                    dto.setCourseNames(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseName())
                        .collect(Collectors.toSet()));
                    return dto;
                });
    }
    // Método para guardar o actualizar un estudiante y convertirlo a DTO
    public StudentDTO saveOrUpdate(StudentDTO studentDTO) {
        // Convertir el DTO a entidad
        Student student = studentMapper.toEntity(studentDTO);

        // Guardar la entidad actualizada
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }

    // Metodo para eliminar un estudiante por su UUID
    public void delete(UUID id) {
        studentRepository.deleteById(id);
    }

    // Metodo para obtener un estudiante por su RUT
    public Optional<StudentDTO> getStudentByRut(String rut) {
        return studentRepository.findByRut(rut)
                .map(student -> {
                    StudentDTO dto = studentMapper.toDTO(student);
                    // Consultar inscripciones para este estudiante
                    dto.setCourseIds(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseId())
                        .collect(Collectors.toSet()));
                    dto.setCourseNames(enrollmentRepository.findByStudentStudentId(student.getStudentId())
                        .stream()
                        .map(enrollment -> enrollment.getCourse().getCourseName())
                        .collect(Collectors.toSet()));
                    return dto;
                });
    }

    // Método para obtener los cursos de un estudiante
    public StudentDTO enrollStudentInCourse(UUID studentId, UUID courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
        return getStudent(studentId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado tras inscripción"));
    }
}
