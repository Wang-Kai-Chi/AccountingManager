package com.ericwang.jaccount;

import java.util.ArrayList;

public class CashFlowRecordRepository {
	private ArrayList<CashFlowRecord> recordList;
	private SingleConsumptionRecordController controller;

	public CashFlowRecordRepository(SingleConsumptionRecordController dbController) {
		recordList = new ArrayList<>();
		controller = dbController;
	}

	public void refresh() {
		recordList.clear();

		for (int i = 1; i < controller.getRows() + 1; i++) {
			recordList.add(new CashFlowRecord(Integer.parseInt(controller.getData(i, 1)),
					Integer.parseInt(controller.getData(i, 2)), controller.getData(i, 3),
					Integer.parseInt(controller.getData(i, 4)), controller.getData(i, 5)));
		}
	}

	public ArrayList<CashFlowRecord> getRecordList() {
		return recordList;
	}
}
