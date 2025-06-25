package com.suiteonix.schoolpaz.department.internal;

import com.suiteonix.schoolpaz.department.DepartmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, DepartmentId> {
    boolean existsById_Id(String idId);
}