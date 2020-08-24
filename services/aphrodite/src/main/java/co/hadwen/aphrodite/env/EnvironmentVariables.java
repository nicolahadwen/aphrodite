package co.hadwen.aphrodite.env;

import libraries.environment.EnvironmentVariable;
import lombok.Getter;

@Getter
public enum EnvironmentVariables implements EnvironmentVariable {
    DB_HOST("DB_HOST"),
    DB_USER_NAME("DB_USER_NAME"),
    DB_PASSWORD("DB_PASSWORD");

    private final String key;

    EnvironmentVariables(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
