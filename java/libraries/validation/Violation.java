package libraries.validation;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Violation {
    private final List<String> fieldPath;
    private final List<ViolationReason> violationReasons;

    public Violation(
            @NonNull String fieldName,
            @NonNull List<ViolationReason> violationReasons) {
        this.fieldPath = Collections.singletonList(fieldName);
        this.violationReasons = violationReasons;
    }

    public Violation(
            @NonNull String fieldName,
            @NonNull ViolationReason violationReason) {
        this.fieldPath = Collections.singletonList(fieldName);
        this.violationReasons = Collections.singletonList(violationReason);
    }
}
