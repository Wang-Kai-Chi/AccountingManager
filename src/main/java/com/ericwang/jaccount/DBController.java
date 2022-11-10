package com.ericwang.jaccount;

import java.sql.*;

public class DBController {
    private ResultSet resultSet;
    private String[] headers;

    public void query(String sql, Connection connection) throws SQLException {
        Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        resultSet = stmt.executeQuery(sql);

        initHeaders(resultSet.getMetaData());
    }

    private void initHeaders(ResultSetMetaData r) throws SQLException {
        int colCount = r.getColumnCount();
        headers = new String[colCount];

        for (int i = 0; i < headers.length; i++) {
            headers[i] = r.getColumnName(i + 1);
        }
    }

    public void add() {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateString("name", "test");
            resultSet.updateString("birthday", "1999-01-01");
            resultSet.updateString("role", "test");
            resultSet.insertRow();
            System.out.println("insert a new row");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void update(int row, String header, String data) {
		try {
			resultSet.absolute(row);
			resultSet.updateString(header, data);
			resultSet.updateRow();
		} catch (SQLException e) {
			System.out.printf("%d : %s :%s\n", row, header, e);
		}
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
        return headers.length;
    }

    public String[] getHeaders() {
        return headers;
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
