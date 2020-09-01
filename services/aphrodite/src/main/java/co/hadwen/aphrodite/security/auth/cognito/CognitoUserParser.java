package co.hadwen.aphrodite.security.auth.cognito;

import lombok.NonNull;

import java.util.Optional;

public class CognitoUserParser {
    private final CognitoJwtTokenPayloadParser payloadParser = new CognitoJwtTokenPayloadParser();

    public Optional<CognitoUser> parse(@NonNull String token) {
        return payloadParser.parse(token)
                .map(CognitoJwtTokenPayload::getUsername)
                .map(CognitoUser::new);
    }
}
