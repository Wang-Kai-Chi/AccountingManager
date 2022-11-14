package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private final CashFlowRecordService cfService;
	private final SingleConsumptionRecordRepository repo;
	private ConsumptionCategoryService ccService;
	private ConsumptionCategoryRepository repo2;

	public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
		System.out.println("connection start");

		repo = new SingleConsumptionRecordRepository();
		repo2 = new ConsumptionCategoryRepository();
		
		start(mySQLConnectionBuilder.getConnection());

		cfService = new CashFlowRecordService(repo);

		ccService = new ConsumptionCategoryService(repo2);
	}

	public void refresh() {
		cfService.refresh();
	}

	public void printData() {
		for (CashFlowRecord c : cfService.getRecordList())
			System.out.println(c);

		for (ConsumptionCategory c : ccService.getRecordList())
			System.out.println(c);
	}
	
	private void start(Connection connection) {
		String sql = "select consumption.*, cc.name as category\r\n"
				+ "from single_consumption_record as consumption\r\n" + "join consumption_category as cc\r\n"
				+ "on consumption.category_id = cc.id";
		
		String sql2 = "SELECT * FROM `consumption_category`";
		try {
			System.out.println("Query command: " + sql);
			repo.query(sql, connection);
			// controller.add();

//        controller.update(controller.getRows(), controller.getHeaders()[1], "John");
//        controller.update(controller.getRows(), controller.getHeaders()[2], "1989-10-29");
//        controller.update(controller.getRows(), controller.getHeaders()[3], "solder");

			repo2.query(sql2, connection);
		} catch (SQLException ignored) {
		}
	}

	public CashFlowRecordService getCashFlowRecordService() {
		return cfService;
	}

	public SingleConsumptionRecordRepository getRepo() {
		return repo;
	}
}
