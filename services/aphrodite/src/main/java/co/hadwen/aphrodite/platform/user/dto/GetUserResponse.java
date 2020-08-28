package co.hadwen.aphrodite.platform.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Data
public class GetUserResponse {
    private final String userId;
}
