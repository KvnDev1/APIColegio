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

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    // Metodo para obtener todos los estudiantes y convertirlos a DTO
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }
    // Metodo para obtener un estudiante por su UUID y convertirlo a DTO
    public Optional<StudentDTO> getStudent(UUID id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDTO);
    }
    // Metodo para guardar o actualizar un estudiante y convertirlo a DTO
    public StudentDTO saveOrUpdate(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
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
                .map(studentMapper::toDTO);
    }
}
