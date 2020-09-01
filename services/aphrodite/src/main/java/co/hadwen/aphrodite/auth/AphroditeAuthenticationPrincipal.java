package co.hadwen.aphrodite.auth;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class AphroditeAuthenticationPrincipal {
    private final String username;
    private final Map<String, PlatformEntity> platforms;
}
