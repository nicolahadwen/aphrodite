package libraries.db.sql;

import libraries.db.sql.DbException.Type;
import libraries.environment.EnvironmentVariableUtils;
import co.hadwen.aphrodite.env.EnvironmentVariables;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class DbFactory {
    private final EnvironmentVariableUtils environmentVariableUtils;

    public DbFactory() {
        this.environmentVariableUtils = new EnvironmentVariableUtils();
    }

    public Db getDb() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        try {
            Connection connection = DriverManager.getConnection(
                    environmentVariableUtils.getRequiredVariable(EnvironmentVariables.DB_HOST),
                    environmentVariableUtils.getRequiredVariable(EnvironmentVariables.DB_USER_NAME),
                    environmentVariableUtils.getRequiredVariable(EnvironmentVariables.DB_PASSWORD));
            return new Db(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Connection failed", e, Type.CONNECTION_FAILURE);
        }
    }
}
