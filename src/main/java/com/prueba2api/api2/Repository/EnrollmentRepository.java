package com.prueba2api.api2.Repository;

import com.prueba2api.api2.Models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    // Método para encontrar todas las inscripciones de un estudiante por su ID
    List<Enrollment> findByStudentStudentId(UUID studentId);
}
