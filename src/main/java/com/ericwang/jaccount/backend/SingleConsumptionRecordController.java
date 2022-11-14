package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class SingleConsumptionRecordController {
	private ArrayList<SingleConsumptionRecord> recordList;
	private SingleConsumptionRecordRepository repo;
	private String[] headers;

	public SingleConsumptionRecordController() {
		recordList = new ArrayList<>();

		addDefaultValues();
		
		headers = new String[] {
				"id", "money", "date", "category_id", "description"
		};
	}

	public SingleConsumptionRecordController(SingleConsumptionRecordRepository repo) {
		this();
		this.repo = repo;
	}
	
	private void addDefaultValues() {
		recordList.add(new SingleConsumptionRecord(1,100,"2022-11-01",1,"","food"));
		recordList.add(new SingleConsumptionRecord(2,150,"2022-11-01",1,"","food"));
		recordList.add(new SingleConsumptionRecord(3,80,"2022-11-02", 2,"water bill","bill"));
		recordList.add(new SingleConsumptionRecord(4,120,"2022-11-02",2,"","bill"));
		recordList.add(new SingleConsumptionRecord(5,180,"2022-11-02",3,"bus","transportation"));
		recordList.add(new SingleConsumptionRecord(6,180,"2022-11-03",1,"breakfast","food"));
		recordList.add(new SingleConsumptionRecord(7, 120, "2022-01-01", 1, "dinner","food"));
	}

	public void refresh() {
		if (repo!=null) {
			recordList.clear();
			
			for (int i = 1; i < repo.getRows() + 1; i++) {
				recordList.add(new SingleConsumptionRecord(Integer.parseInt(repo.getData(i, 1)),
						Integer.parseInt(repo.getData(i, 2)), repo.getData(i, 3),
						Integer.parseInt(repo.getData(i, 4)), repo.getData(i, 5),repo.getData(i, 6)));
			}			
			headers = repo.getHeaders();
		}
	}
	
	public void insertNewRecordIfIdIsZero() {
		if(repo!=null) {
			for(SingleConsumptionRecord c:recordList) {
				if(c.getId() == 0) {
					repo.add(c);
					System.out.println(c);
				}
			}			
		}
		
	}
	
	public void print() {
		for (SingleConsumptionRecord c : recordList)
			System.out.println(c);
	}

	public ArrayList<SingleConsumptionRecord> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
}
