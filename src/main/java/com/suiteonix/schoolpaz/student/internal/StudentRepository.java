package com.suiteonix.schoolpaz.student.internal;

import com.suiteonix.schoolpaz.student.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, StudentId> {
    boolean existsById_Id(String idId);
}