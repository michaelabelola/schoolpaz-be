package com.suiteonix.schoolpaz.staff.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.department.internal.Department;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.staff.StaffId;
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
@Table(name = "staff")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff extends Auditable<Staff> {

    @EmbeddedId
    StaffId id;

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

    @Column(name = "hire_date")
    LocalDate hireDate;

    @Column(name = "position")
    String position;

    @Column(name = "is_lecturer")
    boolean isLecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;
}