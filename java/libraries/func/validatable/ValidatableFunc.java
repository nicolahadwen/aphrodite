package libraries.func.validatable;

import libraries.validation.ValidationResult;
import libraries.validation.Validator;

import java.util.function.Function;

public abstract class ValidatableFunc<T, V> implements Function<T, V> {
    protected abstract V doApply(T param);

    protected abstract Validator<T> createValidator(T param);

    @Override
    public V apply(T param) {
        Validator<T> validator = createValidator(param);
        ValidationResult result = validator.validate(param);
        if (!result.isSuccess()) {
            throw new ValidationException(result);
        }
        return doApply(param);
    }
}
