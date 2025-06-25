package com.suiteonix.schoolpaz.kernel.interfaces;


import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
public @interface DomainObject {
}
