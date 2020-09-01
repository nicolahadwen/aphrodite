package co.hadwen.aphrodite.security.auth.cognito;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class CognitoJwtTokenPayload {
    @SerializedName("sub")
    private String sub;
    @SerializedName("aud")
    private String aud;
    @SerializedName("cognito:groups")
    private List<String> cognitoGroups;
    @SerializedName("identities")
    List<CognitoIdentity> cognitoIdentities;
    @SerializedName("token_use")
    private String tokenUse;
    @SerializedName("iss")
    private String iss;
    @SerializedName("cognito:username")
    private String username;
    @SerializedName("exp")
    private Long expMs;
}
