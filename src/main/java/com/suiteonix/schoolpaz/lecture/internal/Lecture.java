package com.suiteonix.schoolpaz.lecture.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.department.internal.Department;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.lecture.LectureId;
import com.suiteonix.schoolpaz.staff.internal.Staff;
import com.suiteonix.schoolpaz.student.internal.Student;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "lecture")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecture extends Auditable<Lecture> {

    @EmbeddedId
    LectureId id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "code", nullable = false, unique = true)
    String code;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "credits")
    Integer credits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    Staff lecturer;

    @ManyToMany
    @JoinTable(
            name = "lecture_student",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @ToString.Exclude
    Set<Student> students = new HashSet<>();
}