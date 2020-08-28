package co.hadwen.aphrodite.platform;

import co.hadwen.aphrodite.platform.controller.PlatformController;
import co.hadwen.aphrodite.platform.store.PlatformStore;
import co.hadwen.aphrodite.platform.user.UserConfig;
import libraries.hibernate.Hibernate;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        PlatformController.class,
        UserConfig.class
})
@Configuration
public class PlatformConfig {
    @Bean
    PlatformStore platformStore(@NonNull Hibernate hibernate) {
        return new PlatformStore(hibernate);
    }
}
