package com.suiteonix.schoolpaz.school;

import com.suiteonix.schoolpaz.kernel.interfaces.ID;
import jakarta.persistence.Embeddable;

@Embeddable
public record SchoolId(
        String id
) implements ID<String> {
}