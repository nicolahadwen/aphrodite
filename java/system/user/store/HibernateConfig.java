package system.user.store;

import libraries.hibernate.MySqlHibernateConfig;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
public class HibernateConfig extends MySqlHibernateConfig {
    private final String connectionUrl;
    private final String dbUser;
    private final String dbPassword;

    @Override
    public Set<Class> getAnnotatedClasses() {
        return Collections.singleton(UserEntity.class);
    }

    @Override
    public String getConnectionUrl() {
        return connectionUrl;
    }

    @Override
    public String getDbUser() {
        return dbUser;
    }

    @Override
    public String getDbPassword() {
        return dbPassword;
    }
}
