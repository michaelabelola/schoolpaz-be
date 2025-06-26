package com.suiteonix.schoolpaz.timetable.internal;

import com.suiteonix.schoolpaz.timetable.TimetableId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, TimetableId> {
    boolean existsById_Id(String idId);
}