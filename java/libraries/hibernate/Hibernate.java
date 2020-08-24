package libraries.hibernate;

import libraries.environment.EnvironmentVariableUtils;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
    private final HibernateConfig config;
    private SessionFactory sessionFactory;
    private final EnvironmentVariableUtils environmentVariableUtils = new EnvironmentVariableUtils();

    public Hibernate(@NonNull HibernateConfig config) {
        this.config = config;
    }

    private SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            this.sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public Session openSession() {
        return getSessionFactory().openSession();
    }

    public void closeCurrentSession() {
        getCurrentSession().close();
    }

    public void tearDown() {
        closeCurrentSession();
        if (sessionFactory != null) {
            getSessionFactory().close();
        }
    }

    private SessionFactory createSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.url", config.getConnectionUrl());
        cfg.setProperty("hibernate.connection.username", config.getDbUser());
        cfg.setProperty("hibernate.connection.password", config.getDbPassword());
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.current_session_context_class", "thread");
        config.getAnnotatedClasses().forEach(cfg::addAnnotatedClass);
        return cfg.buildSessionFactory();
    }
}
