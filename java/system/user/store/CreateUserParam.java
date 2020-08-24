package system.user.store;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreateUserParam {
    @NonNull
    String email;
    @NonNull
    String password;
    @NonNull
    String username;
}
