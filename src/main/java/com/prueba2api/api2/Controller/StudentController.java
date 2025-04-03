package com.prueba2api.api2.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prueba2api.api2.Models.DTOs.CreateStudentDTO;
import com.prueba2api.api2.Models.DTOs.StudentDTO;
import com.prueba2api.api2.Models.DTOs.UpdateStudentDTO;
import com.prueba2api.api2.Service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/colegio")
public class StudentController {

    private final StudentService studentService;

    // Inyección por constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    // GET: Obtener todos los estudiantes
    @GetMapping("/estudiantes")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    // GET: Obtener un estudiante por ID
    @GetMapping("/estudiante/{rut}")
    public ResponseEntity<?> getByRut(@PathVariable String rut) {
        Optional<StudentDTO> student = studentService.getStudentByRut(rut);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Estudiante no encontrado en la base de datos"));
        }
    }
    
    // PUT: Actualizar un estudiante, solo se puede modificar el nombre y apellido
    @PutMapping("/actualizarEstudiante/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable UUID studentId, @RequestBody @Valid UpdateStudentDTO updateDto) {
        Optional<StudentDTO> existingStudent = studentService.getStudent(studentId);
        if (existingStudent.isPresent()) {
            StudentDTO studentToUpdate = existingStudent.get();
            // Actualizar solo los campos modificables
            studentToUpdate.setName(updateDto.getName());
            studentToUpdate.setLastName(updateDto.getLastName());
            studentService.saveOrUpdate(studentToUpdate);
            return ResponseEntity.ok(Map.of("message", "Estudiante actualizado exitosamente!",
                                                "id", studentId.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Estudiante con ID " + studentId + " no encontrado."));
        }
    }
    
    // POST: Crear un nuevo estudiante
    @PostMapping("/crearEstudiante")
public ResponseEntity<?> createStudent(@RequestBody @Valid CreateStudentDTO createDto) {
    // Verificar si ya existe un estudiante con el mismo RUT
    Optional<StudentDTO> existingStudent = studentService.getStudentByRut(createDto.getRut());
    if (existingStudent.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "ERROR: El rut ingresado ya se encuentra en el sistema"));
    }
    
    // Convertir CreateStudentDTO a StudentDTO
    StudentDTO studentDto = new StudentDTO();
    studentDto.setRut(createDto.getRut());
    studentDto.setName(createDto.getName());
    studentDto.setLastName(createDto.getLastName());
    
    // Guardar el estudiante y obtener el objeto guardado con el UUID asignado
    StudentDTO savedStudent = studentService.saveOrUpdate(studentDto);
    
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of(
                "message", "Estudiante creado exitosamente!",
                "rut", savedStudent.getRut(),
                "id", savedStudent.getStudentId().toString()
            ));
}

    
    
    // DELETE: Eliminar un estudiante por ID
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID studentId) {
        studentService.delete(studentId);
        return ResponseEntity.ok(Map.of("message", "Estudiante eliminado exitosamente!"));
    }
}
