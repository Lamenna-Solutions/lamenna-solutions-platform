package app.lamenna.commons.security;

import java.util.*;

public interface UserContext {
    UUID currentUserId();
    Optional<AuthenticatedUser> currentUser();

}
