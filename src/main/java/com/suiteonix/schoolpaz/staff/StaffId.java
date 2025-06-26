package com.suiteonix.schoolpaz.staff;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record StaffId(
        String id,
        String entityId
) implements SchoolMemberId<String> {
}