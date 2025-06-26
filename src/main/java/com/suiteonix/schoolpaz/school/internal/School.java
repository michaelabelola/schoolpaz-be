package com.suiteonix.schoolpaz.school.internal;

import com.suiteonix.schoolpaz.department.internal.Auditable;
import com.suiteonix.schoolpaz.kernel.interfaces.DomainObject;
import com.suiteonix.schoolpaz.school.SchoolId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Entity
@DomainObject
@RequiredArgsConstructor
@Table(name = "school")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class School extends Auditable<School> {

    @EmbeddedId
    SchoolId id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "website")
    String website;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
}