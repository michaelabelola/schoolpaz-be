package com.suiteonix.schoolpaz.student;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record StudentId(
        String id,
        String entityId
) implements SchoolMemberId<String> {
}