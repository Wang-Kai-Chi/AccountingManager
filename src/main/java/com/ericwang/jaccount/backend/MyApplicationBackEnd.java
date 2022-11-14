package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private final CashFlowRecordService cfService;
	private final SingleConsumptionRecordRepository repo;
	private ConsumptionCategoryService ccService;

	public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
		System.out.println("connection start");

		repo = new SingleConsumptionRecordRepository();
		ApplicationActor applicationActor = new ApplicationActor(repo);
		applicationActor.start(mySQLConnectionBuilder.getConnection());

		cfService = new CashFlowRecordService(applicationActor.getRepo());
		
		ccService = new ConsumptionCategoryService();
	}
	
	public void refresh() {
		cfService.refresh();		
	}

	public void printData() {
		for (CashFlowRecord c : cfService.getRecordList())
			System.out.println(c);
		
		for(ConsumptionCategory c:ccService.getRecordList())
			System.out.println(c);
	}

	public CashFlowRecordService getCashFlowRecordService() {
		return cfService;
	}

	public SingleConsumptionRecordRepository getRepo() {
		return repo;
	}

	private static class ApplicationActor {
		private final SingleConsumptionRecordRepository repo;

		public ApplicationActor(SingleConsumptionRecordRepository repo) {
			this.repo = repo;
		}

		public void start(Connection connection) {
			String sql = "select consumption.*, cc.name as category\r\n"
					+ "from single_consumption_record as consumption\r\n"
					+ "join consumption_category as cc\r\n"
					+ "on consumption.category_id = cc.id";
			try {
				System.out.println("Query command: " + sql);
				repo.query(sql, connection);
				// controller.add();

//            controller.update(controller.getRows(), controller.getHeaders()[1], "John");
//            controller.update(controller.getRows(), controller.getHeaders()[2], "1989-10-29");
//            controller.update(controller.getRows(), controller.getHeaders()[3], "solder");
			} catch (SQLException ignored) {
			}
		}

		public SingleConsumptionRecordRepository getRepo() {
			return repo;
		}
	}
}
