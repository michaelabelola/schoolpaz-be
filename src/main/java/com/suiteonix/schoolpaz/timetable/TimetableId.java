package com.suiteonix.schoolpaz.timetable;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record TimetableId(
        String id,
        String entityId
) implements SchoolMemberId<String> {
}