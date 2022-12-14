package com.ericwang.jaccount.backend.cc;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConsumptionCategoryController {
	private ArrayList<ConsumptionCategory> recordList;
	private String[] headers;
	private ConsumptionCategoryRepository repo;

	public ConsumptionCategoryController() {
		recordList = new ArrayList<>();
		headers = new String[] { "id", "name" };

		addDefaultValue();
	}

	public ConsumptionCategoryController(ConsumptionCategoryRepository repo) {
		this();
		this.repo = repo;
	}

	private void addDefaultValue() {
		recordList.add(new ConsumptionCategory(1, "food"));
		recordList.add(new ConsumptionCategory(2, "bill"));
		recordList.add(new ConsumptionCategory(3, "transportation"));
	}

	public void getFromDB() {
		if (repo != null) {
			try {
				repo.query();
			} catch (SQLException e) {
			}
		}
	}

	public void refreshList() {
		recordList.clear();

		for (int i = 1; i < repo.getRows() + 1; i++) {
			recordList.add(new ConsumptionCategory(Integer.parseInt(repo.getData(i, 1)), repo.getData(i, 2)));
		}

		headers = repo.getHeaders();
	}

	public void print() {
		for (ConsumptionCategory c : recordList)
			System.out.println(c);
	}

	public ArrayList<ConsumptionCategory> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}

}
