package com.prueba2api.api2.Service;

import com.prueba2api.api2.Models.Teacher;
import com.prueba2api.api2.Models.DTOs.TeacherDTO;
import com.prueba2api.api2.Mappers.TeacherMapper;
import com.prueba2api.api2.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    // Obtiene todos los profesores y los mapea a DTO
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Busca un profesor por UUID y lo convierte a DTO
    public Optional<TeacherDTO> getTeacherById(UUID teacherId) {
        return teacherRepository.findById(teacherId)
                .map(teacherMapper::toDTO);
    }

    // Busca un profesor por RUT y lo convierte a DTO
    public Optional<TeacherDTO> getTeacherByRut(String rut) {
        return teacherRepository.findByRut(rut)
                .map(teacherMapper::toDTO);
    }

    // Guarda o actualiza un profesor a partir de TeacherDTO y retorna el objeto guardado en forma de DTO
    public TeacherDTO saveOrUpdate(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDTO(savedTeacher);
    }

    // Elimina un profesor por UUID
    public void delete(UUID teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
