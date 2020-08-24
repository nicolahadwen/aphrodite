package libraries.hibernate;

import java.util.Set;

public interface HibernateConfig {
    Set<Class> getAnnotatedClasses();

    String getConnectionUrl();
    String getDbUser();
    String getDbPassword();
    String getDialect();
    String getCurrentSessionContextClass();
    String getDriverClass();
}
