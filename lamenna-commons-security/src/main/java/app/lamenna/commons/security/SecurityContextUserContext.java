package app.lamenna.commons.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SecurityContextUserContext implements UserContext {
    @Override
    public Optional<AuthenticatedUser> currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof AuthenticatedUser u) {
            return Optional.of(u);
        }
        return Optional.empty();
    }

    @Override
    public UUID currentUserId() {
        return currentUser().map(AuthenticatedUser::id)
                .orElseThrow(() -> new UnauthenticatedException("No authenticated user in context"));
    }
}
