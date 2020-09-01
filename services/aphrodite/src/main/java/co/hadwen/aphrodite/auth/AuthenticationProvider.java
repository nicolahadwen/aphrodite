package co.hadwen.aphrodite.auth;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import co.hadwen.aphrodite.platform.user.store.UserStore;
import co.hadwen.aphrodite.security.auth.cognito.CognitoUser;
import co.hadwen.aphrodite.security.auth.cognito.CognitoUserParser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

@AllArgsConstructor
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private final CognitoUserParser cognitoUserParser;
    private final UserStore userStore;

    @Override
    public AphroditeAuthentication authenticate(Authentication authentication) throws AuthenticationException {
        if (! (authentication instanceof BearerAuthenticationToken)) {
            return null;
        }
        BearerAuthenticationToken authenticationToken = (BearerAuthenticationToken) authentication;
        try {
            CognitoUser cognitoUser = cognitoUserParser.parse(authenticationToken.getCredentials())
                    .orElseThrow(() -> new RuntimeException("Failed to get user from token"));
            Map<String, PlatformEntity> platforms = userStore.getPlatformsForUser(cognitoUser.getUsername());
            return new AphroditeAuthentication(
                    cognitoUser.getUsername(),
                    new AphroditeAuthenticationPrincipal(cognitoUser.getUsername(), platforms),
                    new AphroditeAuthenticationDetails(),
                    true
            );
        } catch (Exception e) {
            System.out.println("Has an error");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(BearerAuthenticationToken.class);
    }
}
