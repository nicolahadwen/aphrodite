package libraries.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class DbConnectionParam {
    @NonNull
    private final String connectionUrl;
    @NonNull
    private final String username;
    @NonNull
    private final String password;
}
