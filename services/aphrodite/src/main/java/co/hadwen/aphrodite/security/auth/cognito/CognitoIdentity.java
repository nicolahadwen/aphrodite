package co.hadwen.aphrodite.security.auth.cognito;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CognitoIdentity {
    @SerializedName("userId")
    private String userId;
    @SerializedName("providerName")
    private String providerName;
    @SerializedName("providerType")
    private String providerType;
    @SerializedName("primary")
    private Boolean primary;
    @SerializedName("dateCreated")
    private Boolean dateCreated;
}
