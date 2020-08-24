package libraries.id;

import java.util.UUID;

public class IdUtils {
    private static final int DEFAULT_ID_LENGTH = 34;
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
