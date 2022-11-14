package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private final CashFlowRecordService cashFlowRecordService;
	private final SingleConsumptionRecordRepository repo;
	private ConsumptionCategoryService consumptionCategoryService;
	private ConsumptionCategoryRepository repo2;

	public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
		System.out.println("connection start");

		repo = new SingleConsumptionRecordRepository();
		repo2 = new ConsumptionCategoryRepository();
		
		start(mySQLConnectionBuilder.getConnection());

		cashFlowRecordService = new CashFlowRecordService(repo);

		consumptionCategoryService = new ConsumptionCategoryService(repo2);
	}

	public void refresh() {
		cashFlowRecordService.refresh();
		consumptionCategoryService.refresh();
	}

	public void printData() {
		for (CashFlowRecord c : cashFlowRecordService.getRecordList())
			System.out.println(c);

		for (ConsumptionCategory c : consumptionCategoryService.getRecordList())
			System.out.println(c);
	}
	
	private void start(Connection connection) {
		String sql = "select * from single_consumption_record";
		
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
		return cashFlowRecordService;
	}

	public ConsumptionCategoryService getConsumptionCategoryService() {
		return consumptionCategoryService;
	}
	
	
}
