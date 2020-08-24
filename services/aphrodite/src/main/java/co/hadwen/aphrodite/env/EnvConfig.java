package co.hadwen.aphrodite.env;

import libraries.environment.EnvironmentVariableUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    @Bean
    EnvironmentVariableUtils environmentVariableUtils() {
        return new EnvironmentVariableUtils();
    }
}
