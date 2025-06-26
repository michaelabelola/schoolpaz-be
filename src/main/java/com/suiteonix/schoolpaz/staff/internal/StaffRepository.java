package com.suiteonix.schoolpaz.staff.internal;

import com.suiteonix.schoolpaz.staff.StaffId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, StaffId> {
    boolean existsById_Id(String idId);
}