package co.hadwen.aphrodite;

import co.hadwen.aphrodite.env.EnvConfig;
import co.hadwen.aphrodite.healthcheck.HealthCheckConfig;
import co.hadwen.aphrodite.hibernate.HibernateConfig;
import co.hadwen.aphrodite.security.GlobalExceptionHandler;
import co.hadwen.aphrodite.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({
        ControllerConfig.class,
        EnvConfig.class,
        HealthCheckConfig.class,
        HibernateConfig.class,
        WebSecurityConfig.class,
        GlobalExceptionHandler.class
})
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        ThymeleafAutoConfiguration.class,
})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
