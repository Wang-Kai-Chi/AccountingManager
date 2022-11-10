package com.ericwang.jaccount;

import java.sql.*;
import java.util.Properties;

class MySQLConnectionBuilder {
    private final Connection connection;

    public MySQLConnectionBuilder(String dbName) throws SQLException {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");

        String url = "jdbc:mysql://localhost:3306/"+dbName;
        connection = DriverManager.getConnection(url, prop);
    }

    public Connection getConnection() {
        return connection;
    }
}
