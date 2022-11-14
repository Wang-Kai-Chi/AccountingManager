package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class ConsumptionCategoryService {
	private ArrayList<ConsumptionCategory> recordList;
	private String[] headers;
	private ConsumptionCategoryRepository repo;

	public ConsumptionCategoryService() {
		recordList = new ArrayList<>();
		headers = new String[] { "id", "name"};
		
		addDefaultValue();
	}
	
	public ConsumptionCategoryService(ConsumptionCategoryRepository repo) {
		this();
		this.repo = repo;
	}

	private void addDefaultValue() {
		recordList.add(new ConsumptionCategory(1, "food"));
		recordList.add(new ConsumptionCategory(2, "bill"));
		recordList.add(new ConsumptionCategory(3, "transportation"));
	}
	
	public void refresh() {
		recordList.clear();

		for (int i = 1; i < repo.getRows() + 1; i++) {
			recordList.add(new ConsumptionCategory(Integer.parseInt(repo.getData(i, 1)), repo.getData(i, 2)));
		}

		headers = repo.getHeaders();
	}

	public ArrayList<ConsumptionCategory> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
	
	
}
