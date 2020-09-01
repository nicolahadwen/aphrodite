package co.hadwen.aphrodite.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AphroditeAuthentication implements Authentication {
    private final String username;
    private final AphroditeAuthenticationPrincipal principal;
    private final AphroditeAuthenticationDetails authenticationDetails;
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getCredentials() {
        return username;
    }

    @Override
    public AphroditeAuthenticationDetails getDetails() {
        return authenticationDetails;
    }

    @Override
    public AphroditeAuthenticationPrincipal getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = b;
    }

    @Override
    public String getName() {
        return username;
    }
}
