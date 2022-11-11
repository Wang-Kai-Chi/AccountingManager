package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private final CashFlowRecordRepository cashFlowRecordRepository;
	private final SingleConsumptionRecordController controller;

	public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
		System.out.println("connection start");

		controller = new SingleConsumptionRecordController();
		ApplicationActor applicationActor = new ApplicationActor(controller);
		applicationActor.start(mySQLConnectionBuilder.getConnection());

		cashFlowRecordRepository = new CashFlowRecordRepository(applicationActor.getController());
	}
	
	public void refresh() {
		cashFlowRecordRepository.refresh();		
	}

	public void printData() {
		for (CashFlowRecord c : cashFlowRecordRepository.getRecordList())
			System.out.println(c);
	}

	public CashFlowRecordRepository getCashFlowRecordRepository() {
		return cashFlowRecordRepository;
	}

	public SingleConsumptionRecordController getController() {
		return controller;
	}

	private static class ApplicationActor {
		private final SingleConsumptionRecordController controller;

		public ApplicationActor(SingleConsumptionRecordController controller) {
			this.controller = controller;
		}

		public void start(Connection connection) {
			String sql = "SELECT * FROM single_consumption_record";
			try {
				System.out.println("Query command: " + sql);
				controller.query(sql, connection);
				// controller.add();

//            controller.update(controller.getRows(), controller.getHeaders()[1], "John");
//            controller.update(controller.getRows(), controller.getHeaders()[2], "1989-10-29");
//            controller.update(controller.getRows(), controller.getHeaders()[3], "solder");
			} catch (SQLException ignored) {
			}
		}

		public SingleConsumptionRecordController getController() {
			return controller;
		}
	}
}
