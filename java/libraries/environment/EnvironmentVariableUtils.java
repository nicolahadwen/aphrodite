package libraries.environment;

import libraries.environment.EnvironmentVariableException.Type;
import lombok.NonNull;

import java.util.Optional;

public class EnvironmentVariableUtils {
    public String getRequiredVariable(@NonNull EnvironmentVariable variable) {
        return Optional.ofNullable(System.getenv(variable.getKey()))
                .filter(val -> !val.isEmpty())
                .orElseThrow(() ->
                        new EnvironmentVariableException(
                                String.format("Variable %s not found", variable.getKey()), Type.NOT_FOUND));
    }
}
