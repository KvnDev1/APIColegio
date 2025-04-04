package com.prueba2api.api2.Service;

import com.prueba2api.api2.Models.Teacher;
import com.prueba2api.api2.Models.DTOs.CreateTeacherDTO;
import com.prueba2api.api2.Models.DTOs.TeacherDTO;
import com.prueba2api.api2.Models.DTOs.UpdateTeacherDTO;
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

    // Crear un profesor a partir de CreateTeacherDTO
    public TeacherDTO createTeacher(CreateTeacherDTO createTeacherDTO) {
        Teacher teacher = teacherMapper.toEntity(createTeacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDTO(savedTeacher);
    }

     // Actualizar un profesor a partir de UpdateTeacherDTO
    public Optional<TeacherDTO> updateTeacher(UUID teacherId, UpdateTeacherDTO updateTeacherDTO) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isPresent()) {
            Teacher teacherToUpdate = teacherOpt.get();
            // Actualizar los campos modificables name, lastname y email (ignorando rut)
            teacherToUpdate.setTeacherName(updateTeacherDTO.getTeacherName());
            teacherToUpdate.setEmail(updateTeacherDTO.getEmail());
            Teacher updatedTeacher = teacherRepository.save(teacherToUpdate);
            return Optional.of(teacherMapper.toDTO(updatedTeacher));
        }
        return Optional.empty();
    }

    // Elimina un profesor por UUID
    public void delete(UUID teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
