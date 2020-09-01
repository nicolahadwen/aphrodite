package co.hadwen.aphrodite.security;

import co.hadwen.aphrodite.auth.AuthenticationProvider;
import co.hadwen.aphrodite.platform.user.store.UserStore;
import co.hadwen.aphrodite.security.auth.cognito.CognitoUserParser;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Bean
    CognitoUserParser cognitoUserParser() {
        return new CognitoUserParser();
    }

    @Bean("authenticationProvider")
    public org.springframework.security.authentication.AuthenticationProvider tokenAuthenticationProvider(
            @NonNull CognitoUserParser cognitoUserParser,
            @NonNull UserStore userStore) {
        return new AuthenticationProvider(cognitoUserParser, userStore);
    }

    @Bean
    public AuthenticationManager authenticationManager(@NonNull
            org.springframework.security.authentication.AuthenticationProvider authenticationProvider) {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }
}
