package com.ericwang.jaccount.backend;

import com.ericwang.jaccount.backend.scr.SingleConsumptionRecord;

import java.sql.*;

public class PrettyConsumptionRecordRepository {
    private ResultSet resultSet;
    private TableHeaders tableHeaders;
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void query(String sql) throws SQLException {
        Statement stmt = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        resultSet = stmt.executeQuery(sql);

        tableHeaders = new TableHeaders(resultSet.getMetaData());
    }

public void add(SingleConsumptionRecord cfr) throws SQLException {
        String sql = "INSERT INTO `single_consumption_record`" +
                " (`amount_of_money`, `date`, `category_id`, `description`) " +
                "VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setInt(1,cfr.getAmount_of_money());
            statement.setString(2, cfr.getDate());
            statement.setInt(3, cfr.getCategory_id());
            statement.setString(4, cfr.getDescription());

            int n = statement.executeUpdate();

            if (n==1)
                System.out.println("insert a new row");
            else
                System.out.println("fail to insert");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
