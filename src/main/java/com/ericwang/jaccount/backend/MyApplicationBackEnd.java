package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private CashFlowRecordService cashFlowRecordService;
	private SingleConsumptionRecordRepository singleConsumptionRecordRepository;
	
	private ConsumptionCategoryService consumptionCategoryService;
	private ConsumptionCategoryRepository consumptionCategoryRepository;

	public MyApplicationBackEnd() {
		System.out.println("connection start");

		singleConsumptionRecordRepository = new SingleConsumptionRecordRepository();
		consumptionCategoryRepository = new ConsumptionCategoryRepository();

		cashFlowRecordService = new CashFlowRecordService(singleConsumptionRecordRepository);

		consumptionCategoryService = new ConsumptionCategoryService(consumptionCategoryRepository);
	}

	public void refresh() {
		cashFlowRecordService.refresh();
		consumptionCategoryService.refresh();
	}

	public void printData() {
		cashFlowRecordService.print();

		consumptionCategoryService.print();
	}
	
	public void querySingleConsumptionRecord(Connection connection) {
		String sql = "SELECT * FROM single_consumption_record";
		try {
			singleConsumptionRecordRepository.query(sql, connection);
		} catch (SQLException e) {
			
		}
	}
	
	public void queryConsumptionCategory(Connection connection) {
		String sql2 = "SELECT * FROM `consumption_category`";
		try {
			consumptionCategoryRepository.query(sql2, connection);
		} catch (SQLException e) {
			
		}
	}
	
	public CashFlowRecordService getCashFlowRecordService() {
		return cashFlowRecordService;
	}

	public ConsumptionCategoryService getConsumptionCategoryService() {
		return consumptionCategoryService;
	}
	
	
}
