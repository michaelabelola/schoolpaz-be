package com.suiteonix.schoolpaz.kernel.config;

import com.suiteonix.schoolpaz.kernel.Principal;
import com.suiteonix.schoolpaz.kernel.interfaces.ID;
import com.suiteonix.schoolpaz.kernel.interfaces.SchoolMemberId;
import jakarta.persistence.Embeddable;
import org.springframework.security.core.context.SecurityContextHolder;

@Embeddable
public record AuditId(String id) implements ID<String> {
    public static final String SYSTEM = "SYSTEM";

    public AuditId() {
        this(null);
    }

    public static AuditId of(String id) {
        if (id == null) return new AuditId();
        return new AuditId(id);
    }

    public static AuditId ofAuthenticatedUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getName() == null)
            return new AuditId();
        try {
            return AuditId.of(((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).id());
        } catch (Exception e) {
            return AuditId.ofSystem();
        }
    }

    private static AuditId of(SchoolMemberId<String> id) {
        return AuditId.of(id.id());
    }

    public static AuditId ofSystem() {
        return new AuditId(SYSTEM);
    }
}
