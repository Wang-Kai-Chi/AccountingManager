package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private final CashFlowRecordService service;
	private final SingleConsumptionRecordRepository repo;

	public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
		System.out.println("connection start");

		repo = new SingleConsumptionRecordRepository();
		ApplicationActor applicationActor = new ApplicationActor(repo);
		applicationActor.start(mySQLConnectionBuilder.getConnection());

		service = new CashFlowRecordService(applicationActor.getRepo());
	}
	
	public void refresh() {
		service.refresh();		
	}

	public void printData() {
		for (CashFlowRecord c : service.getRecordList())
			System.out.println(c);
	}

	public CashFlowRecordService getCashFlowRecordService() {
		return service;
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
			String sql = "SELECT * FROM single_consumption_record";
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
