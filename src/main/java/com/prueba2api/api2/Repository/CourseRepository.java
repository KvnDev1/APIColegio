package com.prueba2api.api2.Repository;

import com.prueba2api.api2.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    // Si quiero buscar el curso mediante UUID debo dejarlo tal cual sin agregar un FindById
}
