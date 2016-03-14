package com.matthewcasperson.elidetest.permissions;

import com.yahoo.elide.security.User;
import com.yahoo.elide.security.checks.UserCheck;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A security check that passes for admin users
 */
public class AdminCheck extends UserCheck {
    private static final Logger LOGGER = Logger.getLogger(AdminCheck.class.getName());

    @Override
    public boolean ok(User user) {
        final AbstractAuthenticationToken springUser = (AbstractAuthenticationToken) user.getOpaqueUser();

        if (springUser != null && springUser.getAuthorities() != null) {
            return springUser.getAuthorities().stream()
                    .anyMatch(a -> "ROLE_ADMIN".equalsIgnoreCase(a.getAuthority()));
        } else {
            LOGGER.log(Level.SEVERE, "The user object was null. Spring security may not be configured correctly.");
        }

        return false;
    }
}
