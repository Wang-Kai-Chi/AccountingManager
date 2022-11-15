package com.ericwang.jaccount.backend.pcr;

import com.ericwang.jaccount.backend.TableHeaders;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecord;

import java.sql.*;

public class PrettyConsumptionRecordRepository {
	private ResultSet resultSet;
	private TableHeaders tableHeaders;
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void query() throws SQLException {
		String sql = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description "
				+ "from single_consumption_record as consumption " + "join consumption_category as cc "
				+ "on consumption.category_id = cc.id;";

		Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = stmt.executeQuery(sql);

		tableHeaders = new TableHeaders(resultSet.getMetaData());
	}

	public void add(SingleConsumptionRecord record) {
		String sql = "INSERT INTO `single_consumption_record`"
				+ " (`amount_of_money`, `date`, `category_id`, `description`) " + "VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, record.getAmount_of_money());
			statement.setString(2, record.getDate());
			statement.setInt(3, record.getCategory_id());
			statement.setString(4, record.getDescription());

			int n = statement.executeUpdate();

			if (n == 1)
				System.out.println("insert a new row");
			else
				System.out.println("fail to insert");
		} catch (SQLException e) {
		}
	}

	public void update(SingleConsumptionRecord record) {
		String sql = "UPDATE single_consumption_record \r\n"
				+ "SET `amount_of_money` = ?,`date` = ?,category_id = ?,description = ?\r\n" + "WHERE id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, record.getAmount_of_money());
			statement.setString(2, record.getDate());
			statement.setInt(3, record.getCategory_id());
			statement.setString(4, record.getDescription());
			statement.setInt(5, record.getId());

			int n = statement.executeUpdate();

			if (n == 1)
				System.out.println("update complete");
			else
				System.out.println("update failed");
		} catch (SQLException e) {
		}
	}
	
	public void delete(PrettyConsumptionRecord record) {
		String sql = "DELETE FROM single_consumption_record WHERE id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, record.getId());

			boolean b = statement.execute();

			if (b)
				System.out.println("delete failed");
			else
				System.out.println("delete complete");
		} catch (SQLException e) {
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
