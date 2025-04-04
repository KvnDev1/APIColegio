package com.prueba2api.api2.Controller;

import com.prueba2api.api2.Models.DTOs.CourseDTO;
import com.prueba2api.api2.Models.DTOs.CreateCourseDTO;
import com.prueba2api.api2.Models.DTOs.UpdateCourseDTO;
import com.prueba2api.api2.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/colegio/cursos")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET: Obtener todos los cursos
    @GetMapping("/todos")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // GET: Obtener un curso por UUID
    @GetMapping("/cursoId/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable UUID courseId) {
        Optional<CourseDTO> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            return ResponseEntity.ok(courseOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Curso no encontrado"));
        }
    }

    // POST: Crear un nuevo curso usando CreateCourseDTO
    @PostMapping("/crear")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CreateCourseDTO createCourseDTO) {
        CourseDTO savedCourse = courseService.createCourse(createCourseDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Curso creado exitosamente",
                             "id", savedCourse.getCourseId().toString(),
                             "courseName", savedCourse.getCourseName()));
    }

    // PUT: Actualizar un curso usando UpdateCourseDTO
    @PutMapping("/actualizar/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable UUID courseId,
                                          @RequestBody @Valid UpdateCourseDTO updateCourseDTO) {
        Optional<CourseDTO> courseOpt = courseService.updateCourse(courseId, updateCourseDTO);
        if (courseOpt.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Curso actualizado exitosamente",
                                              "id", courseId.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Curso no encontrado"));
        }
    }

    // DELETE: Eliminar un curso por UUID
    @DeleteMapping("/eliminar/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable UUID courseId) {
        courseService.delete(courseId);
        return ResponseEntity.ok(Map.of("message", "Curso eliminado exitosamente"));
    }
}
