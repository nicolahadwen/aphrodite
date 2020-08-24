package co.hadwen.aphrodite.healthcheck;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HealthCheckController.class
})
public class HealthCheckConfig {

}
