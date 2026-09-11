package app.lamenna.core.error;

import java.time.Instant;
import java.util.*;

public record ApiError(
        Instant timestamp,
        int code,
        String message,
        String error,
        String path,
        List<FieldError> fieldErrors
) {
    public record FieldError(String field, String message) {}

    public static ApiError of(int code, String message, String error, String path) {
        return new ApiError(Instant.now(), code, message, error, path, fieldErrors);
    }
}
