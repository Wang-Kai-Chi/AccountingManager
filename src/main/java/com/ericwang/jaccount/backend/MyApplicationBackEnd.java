package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
	private CashFlowRecordService rawRecordService;
	private SingleConsumptionRecordRepository singleConsumptionRecordRepository;
	
	private ConsumptionCategoryService consumptionCategoryService;
	private ConsumptionCategoryRepository consumptionCategoryRepository;

	public MyApplicationBackEnd() {
		System.out.println("connection start");

		singleConsumptionRecordRepository = new SingleConsumptionRecordRepository();
		consumptionCategoryRepository = new ConsumptionCategoryRepository();

		rawRecordService = new CashFlowRecordService(singleConsumptionRecordRepository);

		consumptionCategoryService = new ConsumptionCategoryService(consumptionCategoryRepository);
	}

	public void refresh() {
		rawRecordService.refresh();
		consumptionCategoryService.refresh();
	}

	public void printData() {
		rawRecordService.print();

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
	
	public CashFlowRecordService getRawRecordService() {
		return rawRecordService;
	}

	public ConsumptionCategoryService getConsumptionCategoryService() {
		return consumptionCategoryService;
	}
	
	
}
