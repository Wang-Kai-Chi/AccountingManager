package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class ConsumptionCategoryService {
	private ArrayList<ConsumptionCategory> recordList;
	private String[] headers;

	public ConsumptionCategoryService() {
		recordList = new ArrayList<>();
		headers = new String[] { "id", "name"};
		
		addDefaultValue();
	}
	
	private void addDefaultValue() {
		recordList.add(new ConsumptionCategory(1, "food"));
		recordList.add(new ConsumptionCategory(2, "bill"));
		recordList.add(new ConsumptionCategory(3, "transportation"));
		recordList.add(new ConsumptionCategory(4, "clothes"));
		recordList.add(new ConsumptionCategory(5, "education"));
		recordList.add(new ConsumptionCategory(6, "investment"));
	}

	public ArrayList<ConsumptionCategory> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
	
	
}
