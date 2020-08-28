package libraries.hibernate;

public abstract class MySqlHibernateConfig implements HibernateConfig {

    @Override
    public String getDialect() {
        return "org.hibernate.dialect.MySQL8Dialect";
    }

    @Override
    public String getCurrentSessionContextClass() {
        return "thread";
    }

    @Override
    public String getDriverClass() {
        return "com.mysql.jdbc.Driver";
    }
}
