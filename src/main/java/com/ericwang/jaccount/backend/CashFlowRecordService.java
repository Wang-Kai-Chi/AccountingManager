package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class CashFlowRecordService {
	private ArrayList<CashFlowRecord> recordList;
	private SingleConsumptionRecordRepository repo;
	private String[] headers;

	public CashFlowRecordService() {
		recordList = new ArrayList<>();

		addDefaultValues();
		
		headers = new String[] {
				"id", "money", "date", "category_id", "description"
		};
	}

	public CashFlowRecordService(SingleConsumptionRecordRepository repo) {
		this();
		this.repo = repo;
	}
	
	private void addDefaultValues() {
		recordList.add(new CashFlowRecord(1,100,"2022-11-01",1,"",""));
		recordList.add(new CashFlowRecord(2,150,"2022-11-01",1,"",""));
		recordList.add(new CashFlowRecord(3,80,"2022-11-02", 2,"water bill",""));
		recordList.add(new CashFlowRecord(4,120,"2022-11-02",2,"",""));
		recordList.add(new CashFlowRecord(5,180,"2022-11-02",3,"bus",""));
		recordList.add(new CashFlowRecord(6,180,"2022-11-03",1,"breakfast",""));
		recordList.add(new CashFlowRecord(7, 120, "2022-01-01", 1, "dinner",""));
	}

	public void refresh() {
		if (repo!=null) {
			recordList.clear();
			
			for (int i = 1; i < repo.getRows() + 1; i++) {
				recordList.add(new CashFlowRecord(Integer.parseInt(repo.getData(i, 1)),
						Integer.parseInt(repo.getData(i, 2)), repo.getData(i, 3),
						Integer.parseInt(repo.getData(i, 4)), repo.getData(i, 5),repo.getData(i, 6)));
			}			
			headers = repo.getHeaders();
		}
	}
	
	public void insertNewRecordIfIdIsZero() {
		if(repo!=null) {
			for(CashFlowRecord c:recordList) {
				if(c.getId() == 0) {
					repo.add(c);
					System.out.println(c);
				}
			}			
		}
		
	}

	public ArrayList<CashFlowRecord> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
}
