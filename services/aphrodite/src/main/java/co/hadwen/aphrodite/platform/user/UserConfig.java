package co.hadwen.aphrodite.platform.user;

import co.hadwen.aphrodite.platform.user.store.UserStore;
import libraries.hibernate.Hibernate;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        UserController.class
})
public class UserConfig {
    @Bean
    UserStore userStore(
            @NonNull Hibernate hibernate) {
        return new UserStore(hibernate);
    }
}
