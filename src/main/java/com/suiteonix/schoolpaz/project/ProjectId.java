package com.suiteonix.schoolpaz.project;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProjectId(
        String id,
        String entityId
) implements SchoolMemberId<String> {
}