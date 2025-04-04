package com.prueba2api.api2.Controller;

import com.prueba2api.api2.Models.DTOs.EnrollmentDTO;
import com.prueba2api.api2.Service.EnrollmentService;
import jakarta.validation.Valid;
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

    // Endpoint para inscribir a un estudiante en un curso
    @PostMapping("/crear")
    public ResponseEntity<EnrollmentDTO> enrollStudent(@RequestBody @Valid Map<String, UUID> payload) {
        UUID studentId = payload.get("studentId");
        UUID courseId = payload.get("courseId");
        EnrollmentDTO enrollmentDTO = enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(enrollmentDTO);
    }

    // Endpoint para obtener las inscripciones de un estudiante
    @GetMapping("/estudiante/{studentId}")
    public ResponseEntity<?> getEnrollmentsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }
}
