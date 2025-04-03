package com.prueba2api.api2.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba2api.api2.Models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByRut(String rut);

}
