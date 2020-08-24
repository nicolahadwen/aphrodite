package co.hadwen.aphrodite;

import co.hadwen.aphrodite.env.EnvConfig;
import co.hadwen.aphrodite.user.UserConfig;
import co.hadwen.aphrodite.user.UserController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        UserConfig.class
})
@Configuration
public class ControllerConfig {

}
