package libraries.func.validatable;

import libraries.validation.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.NonNull;

public class ValidationException extends RuntimeException {
    @NonNull
    private final ValidationResult result;

    public ValidationException(@NonNull ValidationResult result) {
        this.result = result;
    }

    public ValidationException(@NonNull String message, @NonNull ValidationResult result) {
        super(message);
        this.result = result;
    }

    public ValidationException(
            @NonNull String message,
            @NonNull Throwable cause,
            @NonNull ValidationResult result) {
        super(message, cause);
        this.result = result;
    }

    public ValidationException(
            @NonNull Throwable cause, @NonNull ValidationResult result) {
        super(cause);
        this.result = result;
    }

    public ValidationException(
            @NonNull String message,
            @NonNull Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            @NonNull ValidationResult result) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.result = result;
    }
}
