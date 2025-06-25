package com.suiteonix.schoolpaz.department.internal;

import com.suiteonix.schoolpaz.department.DepartmentId;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
