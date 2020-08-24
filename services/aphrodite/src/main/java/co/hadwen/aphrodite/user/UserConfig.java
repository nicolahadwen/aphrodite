package co.hadwen.aphrodite.user;

import co.hadwen.aphrodite.env.EnvironmentVariables;
import libraries.environment.EnvironmentVariableUtils;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import system.user.store.HibernateConfig;
import system.user.store.UserStore;
import system.user.store.UserStoreFactory;

@Configuration
@Import({
        UserController.class
})
public class UserConfig {

    @Bean
    HibernateConfig hibernateConfig(@NonNull EnvironmentVariableUtils utils) {
        return new HibernateConfig(
                utils.getRequiredVariable(EnvironmentVariables.DB_HOST),
                utils.getRequiredVariable(EnvironmentVariables.DB_USER_NAME),
                utils.getRequiredVariable(EnvironmentVariables.DB_PASSWORD)
        );
    };

    @Bean
    UserStore userStore(@NonNull HibernateConfig hibernateConfig) {
        return new UserStoreFactory(hibernateConfig).getClient();
    }
}
