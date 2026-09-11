package app.lamenna.commons.security;

import java.util.*;

public record AuthenticatedUser(
        UUID userId,
        String username,
        String email,
        String password,
        Set<String> role
) {
}
