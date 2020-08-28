package co.hadwen.aphrodite.hibernate;

import co.hadwen.aphrodite.env.EnvironmentVariables;
import co.hadwen.aphrodite.platform.user.entity.UserEntity;
import libraries.hibernate.Hibernate;
import libraries.hibernate.MySqlHibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.hadwen.aphrodite.platform.entity.PlatformEntity;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class HibernateConfig {
    private static final HashSet<Class> ANNOTATED_CLASSES = new HashSet<>();
    static {
        ANNOTATED_CLASSES.add(PlatformEntity.class);
        ANNOTATED_CLASSES.add(UserEntity.class);
    }

    @Bean
    Hibernate hibernate() {
        return new Hibernate(
                new MySqlHibernateConfig() {
                    @Override
                    public Set<Class> getAnnotatedClasses() {
                        return ANNOTATED_CLASSES;
                    }

                    @Override
                    public String getConnectionUrl() {
                        return System.getenv(EnvironmentVariables.DB_HOST.getKey());
                    }

                    @Override
                    public String getDbUser() {
                        return System.getenv(EnvironmentVariables.DB_USER_NAME.getKey());
                    }

                    @Override
                    public String getDbPassword() {
                        return System.getenv(EnvironmentVariables.DB_PASSWORD.getKey());
                    }

                    @Override
                    public boolean allowSchemaUpdates() {
                        return true;
                    }
                }
        );
    }
}
