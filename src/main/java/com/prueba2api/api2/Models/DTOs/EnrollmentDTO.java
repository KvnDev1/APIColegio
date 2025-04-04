package com.prueba2api.api2.Models.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private UUID enrollmentId;
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime enrollmentDate;
}
