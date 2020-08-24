package co.hadwen.aphrodite;

import co.hadwen.aphrodite.exception.NotFoundException;
import co.hadwen.aphrodite.exception.ValidationException;
import co.hadwen.aphrodite.exception.dto.ValidationErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ValidationException.class })
    ResponseEntity<ValidationErrorDTO> validationError(ValidationException e) {
        System.err.println(e.getMessage());
        return ResponseEntity.badRequest().body(e.getErrorDTO());
    }

    @ExceptionHandler({ NotFoundException.class })
    ResponseEntity<ValidationErrorDTO> notFound(NotFoundException e) {
        System.err.println(e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
