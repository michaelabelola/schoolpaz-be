package com.suiteonix.schoolpaz.department.internal;

import com.suiteonix.schoolpaz.kernel.config.AuditId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@ToString
@MappedSuperclass
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T extends Auditable<T>> extends AbstractAggregateRoot<T> {
    @CreatedBy
    @Embedded
    @Column(name = "created_by")
    @AttributeOverride(name = "id", column = @Column(name = "created_by"))
    private AuditId createdBy;

    @CreatedDate
    @Column(name = "created_date")
    private Instant createdDate;

    @LastModifiedBy
    @Embedded
    @Column(name = "last_modified_by")
    @AttributeOverride(name = "id", column = @Column(name = "last_modified_by"))
    private AuditId lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
}
