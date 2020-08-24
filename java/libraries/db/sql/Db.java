package libraries.db.sql;

import lombok.NonNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db  {
    @NonNull
    private final Connection connection;

    Db(@NonNull Connection connection) {
        this.connection = connection;
    }

    public ResultSet query(@NonNull String sql) throws SQLException {
        return connection.prepareStatement(sql).executeQuery();
    }

    public int update(@NonNull String sql) throws SQLException {
        return connection.prepareStatement(sql).executeUpdate();
    }
}