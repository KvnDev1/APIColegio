package com.prueba2api.api2.Controller;

import com.prueba2api.api2.Models.DTOs.CreateTeacherDTO;
import com.prueba2api.api2.Models.DTOs.TeacherDTO;
import com.prueba2api.api2.Models.DTOs.UpdateTeacherDTO;
import com.prueba2api.api2.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/colegio/profesores")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET: Obtener todos los profesores
    @GetMapping("/todos")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    // GET: Obtener un profesor por UUID
    @GetMapping("/buscarId/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable UUID teacherId) {
        Optional<TeacherDTO> teacherOpt = teacherService.getTeacherById(teacherId);
        if (teacherOpt.isPresent()) {
            return ResponseEntity.ok(teacherOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Profesor no encontrado"));
        }
    }

    // GET: Obtener un profesor por RUT
    @GetMapping("/buscarRut/{rut}")
    public ResponseEntity<?> getTeacherByRut(@PathVariable String rut) {
        Optional<TeacherDTO> teacherOpt = teacherService.getTeacherByRut(rut);
        if (teacherOpt.isPresent()) {
            return ResponseEntity.ok(teacherOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Profesor no encontrado"));
        }
    }

    // POST: Crear un nuevo profesor usando CreateTeacherDTO
    @PostMapping("/crear")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid CreateTeacherDTO createTeacherDTO) {
        TeacherDTO savedTeacher = teacherService.createTeacher(createTeacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Profesor creado exitosamente",
                             "id", savedTeacher.getTeacherId().toString(),
                             "rut", savedTeacher.getRut()));
    }

    // PUT: Actualizar un profesor usando UpdateTeacherDTO
    @PutMapping("/actualizar/{teacherId}")
    public ResponseEntity<?> updateTeacher(@PathVariable UUID teacherId,
                                           @RequestBody @Valid UpdateTeacherDTO updateTeacherDTO) {
        Optional<TeacherDTO> teacherOpt = teacherService.updateTeacher(teacherId, updateTeacherDTO);
        if (teacherOpt.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Profesor actualizado exitosamente",
                                              "id", teacherId.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Profesor no encontrado"));
        }
    }

    // DELETE: Eliminar un profesor por UUID
    @DeleteMapping("/eliminar/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable UUID teacherId) {
        teacherService.delete(teacherId);
        return ResponseEntity.ok(Map.of("message", "Profesor eliminado exitosamente"));
    }
}
