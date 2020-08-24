package co.hadwen.aphrodite.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Data
public class User {
    private final String userId;
    private final String username;
    private final String email;
}
