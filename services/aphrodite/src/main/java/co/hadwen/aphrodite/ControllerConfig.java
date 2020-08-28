package co.hadwen.aphrodite;

import co.hadwen.aphrodite.platform.PlatformConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        PlatformConfig.class
})
@Configuration
public class ControllerConfig {

}
