package com.suiteonix.schoolpaz.lecture;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;

@Embeddable
public record LectureId(
        String id,
        String entityId
) implements SchoolMemberId<String> {
}