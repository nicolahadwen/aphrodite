package co.hadwen.aphrodite.user.dto;

import co.hadwen.aphrodite.Dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Data
public class CreateUserRequest extends Dto {
    private String username;
    private String email;
    private String password;
}
