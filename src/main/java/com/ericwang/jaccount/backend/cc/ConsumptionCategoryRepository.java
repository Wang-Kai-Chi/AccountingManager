package com.ericwang.jaccount.backend.cc;

import com.ericwang.jaccount.backend.TableHeaders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsumptionCategoryRepository {
	private ResultSet resultSet;
    private TableHeaders tableHeaders;
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void query() throws SQLException {
         String sql = "SELECT * FROM `consumption_category`";
        Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        resultSet = stmt.executeQuery(sql);

        tableHeaders = new TableHeaders(resultSet.getMetaData());
    }
    
    public int getRows() {
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int getCols() {
        return tableHeaders.getHeaders().length;
    }

    public String[] getHeaders() {
        return tableHeaders.getHeaders();
    }

    public String getData(int row, int col) {
        try {
            resultSet.absolute(row);
            return resultSet.getString(col);
        } catch (SQLException e) {
            System.out.printf("%d : %d :%s\n", row, col, e);
            return "no data";
        }
    }
}
