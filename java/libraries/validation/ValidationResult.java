package libraries.validation;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class ValidationResult {
    public static ValidationResult SUCCESS = new ValidationResult(Collections.emptyList());
    @NonNull
    private final List<Violation> violations;

    public boolean isSuccess() {
        return violations.isEmpty();
    }
}
