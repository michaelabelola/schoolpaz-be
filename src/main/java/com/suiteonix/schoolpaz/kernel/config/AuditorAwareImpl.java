package com.suiteonix.schoolpaz.kernel.config;

import com.suiteonix.schoolpaz.kernel.Principal;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AuditorAwareImpl implements AuditorAware<AuditId> {

    @NotNull
    @Override
    public Optional<AuditId> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getName() == null)
            return Optional.of(AuditId.ofSystem());
        try {
            return Optional.of(AuditId.ofAuthenticatedUser());
        } catch (Exception e) {
            return Optional.of(AuditId.of(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
    }
}