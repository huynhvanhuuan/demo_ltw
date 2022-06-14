package vn.edu.hcmuaf.fit.database;

import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection implements IConnectionPool {
    private static final int MAX_TIMEOUT = 30;
    private static final int MAX_POOL_SIZE = 10;
    private final String uid;
    private final String pwd;
    private final String database;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public static DbConnection init(String uid, String pwd, String database) {
        List<Connection> pool = new ArrayList<>(MAX_POOL_SIZE);
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            pool.add(createConnection(uid, pwd, database));
        }
        return new DbConnection(uid, pwd, database, pool);
    }

    private DbConnection(String uid, String pwd, String database, List<Connection> pool) {
        this.uid = uid;
        this.pwd = pwd;
        this.database = database;
        this.connectionPool = pool;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connectionPool.isEmpty()) {
                if (usedConnections.size() < MAX_POOL_SIZE) {
                    connectionPool.add(createConnection(uid, pwd, database));
                } else {
                    DbManager.connectionPool = DbConnection.init(uid, pwd, database);
                    // throw new RuntimeException("Maximum pool size reached, no available connections!");
                }
            }
            Connection connection = connectionPool.remove(0);
            if (connection == null || !connection.isValid(MAX_TIMEOUT)) {
                connection = createConnection(uid, pwd, database);
            }
            usedConnections.add(connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shutdown() {
        try {
            usedConnections.forEach(this::releaseConnection);
            for (Connection c : connectionPool) c.close();
            connectionPool.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        connectionPool.add(connection);
        usedConnections.remove(connection);
    }

    private static Connection createConnection(String uid, String pwd, String database) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost/" + database, uid, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
