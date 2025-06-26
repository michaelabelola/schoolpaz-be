package com.suiteonix.schoolpaz.project.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.department.internal.Department;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.project.ProjectId;
import com.suiteonix.schoolpaz.staff.internal.Staff;
import com.suiteonix.schoolpaz.student.internal.Student;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project extends Auditable<Project> {

    @EmbeddedId
    ProjectId id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "status")
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    Staff supervisor;

    @ManyToMany
    @JoinTable(
            name = "project_student",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @ToString.Exclude
    Set<Student> students = new HashSet<>();
}