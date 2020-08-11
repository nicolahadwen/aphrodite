package co.hadwen.hera;

import co.hadwen.hera.user.UserController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(UserController.class)
@Configuration
public class ControllerConfig {

}
