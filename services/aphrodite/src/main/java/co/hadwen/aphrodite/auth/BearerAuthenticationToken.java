package co.hadwen.aphrodite.auth;

import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BearerAuthenticationToken extends UsernamePasswordAuthenticationToken {

    BearerAuthenticationToken(
            @NonNull String token) {
        super(token, token);
    }

    BearerAuthenticationToken(
            @NonNull String token,
            @NonNull Collection<? extends GrantedAuthority> authorities) {
        super(token, token, authorities);
    }

    @Override
    public String getCredentials() {
        return (String) super.getCredentials();
    }
}
