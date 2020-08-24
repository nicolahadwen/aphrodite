package libraries.environment;

import lombok.NonNull;

public class EnvironmentVariableException extends RuntimeException {
    @NonNull
    private final Type type;

    public EnvironmentVariableException(@NonNull Type type) {
        this.type = type;
    }

    public EnvironmentVariableException(
            @NonNull String message,
            @NonNull Type type) {
        super(message);
        this.type = type;
    }

    public EnvironmentVariableException(
            @NonNull String message,
            @NonNull Throwable cause,
            @NonNull Type type) {
        super(message, cause);
        this.type = type;
    }

    public EnvironmentVariableException(
            @NonNull Throwable cause,
            @NonNull Type type) {
        super(cause);
        this.type = type;
    }

    public EnvironmentVariableException(
            @NonNull String message,
            @NonNull Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            @NonNull Type type) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
    }

    public enum Type {
        NOT_FOUND
    }
}
