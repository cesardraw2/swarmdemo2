package com.matthewcasperson.elidetest.permissions;

import com.yahoo.elide.security.User;
import com.yahoo.elide.security.checks.UserCheck;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * A security check that passes for admin users
 */
public class AdminCheck extends UserCheck {
    @Override
    public boolean ok(User user) {
        final AbstractAuthenticationToken springUser = (AbstractAuthenticationToken) user.getOpaqueUser();
        return springUser.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equalsIgnoreCase(a.getAuthority()));

    }
}
