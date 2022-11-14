package com.ericwang.jaccount.backend;

import java.sql.*;

public class SingleConsumptionRecordRepository {
    private ResultSet resultSet;
    private TableHeaders tableHeaders;

    public void query(String sql, Connection connection) throws SQLException {
        Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        resultSet = stmt.executeQuery(sql);

        tableHeaders = new TableHeaders(resultSet.getMetaData());
    }

    public void add(SingleConsumptionRecord cfr) {
        try {
            resultSet.moveToInsertRow();
            String[] headers = tableHeaders.getHeaders();
            resultSet.updateInt(headers[1], cfr.getAmount_of_money());
            resultSet.updateString(headers[2], cfr.getDate());
            resultSet.updateInt(headers[3], cfr.getCategory_id());
            resultSet.updateString(headers[4], cfr.getDescription());
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