package com.suiteonix.schoolpaz.school.internal;

import com.suiteonix.schoolpaz.school.SchoolId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, SchoolId> {
    boolean existsById_Id(String idId);
}