package vn.edu.hcmuaf.fit.database;

import java.sql.Connection;

public interface IConnectionPool {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
