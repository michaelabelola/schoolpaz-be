package com.suiteonix.schoolpaz.department.internal;

import com.suiteonix.schoolpaz.department.DepartmentId;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.school.internal.School;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "dept")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department extends Auditable<Department> {

    @EmbeddedId
    DepartmentId id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    School school;
}
