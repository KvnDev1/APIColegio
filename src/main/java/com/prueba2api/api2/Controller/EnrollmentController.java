package com.prueba2api.api2.Controller;

import com.prueba2api.api2.Models.DTOs.EnrollmentDTO;
import com.prueba2api.api2.Service.EnrollmentService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/colegio/inscripciones")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // POST Crear nueva inscripción
    @PostMapping("/crear")
    public ResponseEntity<?> enrollStudent(@RequestBody @Valid Map<String, UUID> payload) {
        UUID studentId = payload.get("studentId");
        UUID courseId = payload.get("courseId");
        EnrollmentDTO enrollmentDTO = enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Inscripcion creada exitosamente!",
                        "enrollmentId", enrollmentDTO.getEnrollmentId().toString(),
                        "studentId", enrollmentDTO.getStudentId().toString(),
                        "courseId", enrollmentDTO.getCourseId().toString(),
                        "enrollmentDate", enrollmentDTO.getEnrollmentDate().toString()
                ));
    }

    // GET Inscripción por UUID de Estudiante
    @GetMapping("/estudiante/{studentId}")
    public ResponseEntity<?> getEnrollmentsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }
}
