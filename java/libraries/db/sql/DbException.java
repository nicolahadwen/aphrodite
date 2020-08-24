package libraries.db.sql;

import lombok.NonNull;

public class DbException extends RuntimeException {
    @NonNull
    private final Type type;

    public enum Type {
        CONNECTION_FAILURE
    }

    public DbException(@NonNull Type type) {
        this.type = type;
    }

    public DbException(@NonNull String message, @NonNull Type type) {
        super(message);
        this.type = type;
    }

    public DbException(@NonNull String message, @NonNull Throwable cause, @NonNull Type type) {
        super(message, cause);
        this.type = type;
    }

    public DbException(@NonNull Throwable cause, @NonNull Type type) {
        super(cause);
        this.type = type;
    }

    public DbException(
            @NonNull String message,
            @NonNull Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            @NonNull Type type) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
    }
}
