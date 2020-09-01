package co.hadwen.aphrodite.security.auth.cognito;

import com.google.gson.Gson;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.NonNull;

import java.text.ParseException;
import java.util.Optional;

public class CognitoJwtTokenPayloadParser {
    private static final Gson gson = new Gson();

    public Optional<CognitoJwtTokenPayload> parse(@NonNull String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            if (jwt instanceof SignedJWT) {
                SignedJWT signedJWT = (SignedJWT) jwt;
                Payload payload = signedJWT.getPayload();
                CognitoJwtTokenPayload cognitoJwtToken = gson.fromJson(payload.toString(), CognitoJwtTokenPayload.class);
                cognitoJwtToken.getCognitoIdentities().forEach(identity -> System.out.println(identity.toString()));
                return Optional.of(cognitoJwtToken);
            }
            return Optional.empty();
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
