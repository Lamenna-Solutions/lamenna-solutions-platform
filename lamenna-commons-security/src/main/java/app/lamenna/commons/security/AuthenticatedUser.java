package app.lamenna.commons.security;

import java.util.*;

public record AuthenticatedUser(
        UUID id,
//        String username,
        String email,
//        String password,
        Set<String> roles
) {
}
