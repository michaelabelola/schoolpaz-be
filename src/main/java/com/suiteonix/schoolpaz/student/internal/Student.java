package com.suiteonix.schoolpaz.student.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.department.internal.Department;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.student.StudentId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Auditable<Student> {

    @EmbeddedId
    StudentId id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "address")
    String address;

    @Column(name = "enrollment_date")
    LocalDate enrollmentDate;

    @Column(name = "graduation_date")
    LocalDate graduationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;
}