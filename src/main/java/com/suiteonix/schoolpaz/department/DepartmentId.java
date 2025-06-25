package com.suiteonix.schoolpaz.department;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record DepartmentId(
        String id,
        String entityId
) implements SchoolMemberId<String> {

}
