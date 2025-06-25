package com.suiteonix.schoolpaz.kernel;

import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;

public record Principal(
        SchoolMemberId<String> id
) {
}
