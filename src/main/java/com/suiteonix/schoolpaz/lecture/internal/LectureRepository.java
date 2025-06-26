package com.suiteonix.schoolpaz.lecture.internal;

import com.suiteonix.schoolpaz.lecture.LectureId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, LectureId> {
    boolean existsById_Id(String idId);
}