package libraries.validation;

public interface Validator<T> {
    ValidationResult validate(T param);
}
