package co.hadwen.hera.exception;

import co.hadwen.hera.Dto;
import co.hadwen.hera.exception.dto.ValidationErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private static final String FIELD_DELIMITER = ", ";
    private static final String FIELD_MESSAGE_FORMAT = "%s: %s";
    private final ValidationErrorDTO errorDTO;

    @Override
    public String getMessage() {
        return errorDTO.getInvalidValues().stream()
                .map(Dto::toJsonString)
                .collect(Collectors.joining(FIELD_DELIMITER));
    }
}
