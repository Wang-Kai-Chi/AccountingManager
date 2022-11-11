package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class CashFlowRecordRepository {
	private ArrayList<CashFlowRecord> recordList;
	private SingleConsumptionRecordController controller;
	private String[] headers;

	public CashFlowRecordRepository(SingleConsumptionRecordController dbController) {
		recordList = new ArrayList<>();
		controller = dbController;
		
		addDefaultValues();
		
		headers = new String[] {
			"id", "money", "date", "category_id", "description"
		};
	}
	
	private void addDefaultValues() {
		recordList.add(new CashFlowRecord(1,100,"2022-11-01",1,""));
		recordList.add(new CashFlowRecord(2,150,"2022-11-01",1,""));
		recordList.add(new CashFlowRecord(3,80,"2022-11-02", 2,"water bill"));
		recordList.add(new CashFlowRecord(4,120,"2022-11-02",2,""));
		recordList.add(new CashFlowRecord(5,180,"2022-11-02",3,"bus"));
		recordList.add(new CashFlowRecord(6,180,"2022-11-03",1,"breakfast"));
		recordList.add(new CashFlowRecord(7, 120, "2022-01-01", 1, "dinner"));
	}

	public void refresh() {
		recordList.clear();

		for (int i = 1; i < controller.getRows() + 1; i++) {
			recordList.add(new CashFlowRecord(Integer.parseInt(controller.getData(i, 1)),
					Integer.parseInt(controller.getData(i, 2)), controller.getData(i, 3),
					Integer.parseInt(controller.getData(i, 4)), controller.getData(i, 5)));
		}

		headers = controller.getHeaders();
	}

	public ArrayList<CashFlowRecord> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
}
