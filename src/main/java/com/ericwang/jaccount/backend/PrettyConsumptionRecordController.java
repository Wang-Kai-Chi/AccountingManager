package com.ericwang.jaccount.backend;

import java.util.ArrayList;

public class PrettyConsumptionRecordController {
    private ArrayList<PrettyConsumptionRecord> recordList;
	private PrettyConsumptionRecordRepository repo;
	private String[] headers;

    public PrettyConsumptionRecordController(){
        recordList = new ArrayList<>();
        headers = new String[2];
    }

    public PrettyConsumptionRecordController(PrettyConsumptionRecordRepository repo) {
        this();
        this.repo = repo;
    }

    public void refresh() {
		if (repo!=null) {
			recordList.clear();

			for (int i = 1; i < repo.getRows() + 1; i++) {
				recordList.add(new PrettyConsumptionRecord(
                        Integer.parseInt(repo.getData(i, 1)),
						repo.getData(i, 2),
                        repo.getData(i, 3),
						repo.getData(i, 4)));
			}
			headers = repo.getHeaders();
		}
	}

    public void print() {
		for (PrettyConsumptionRecord c : recordList)
			System.out.println(c);
	}

	public ArrayList<PrettyConsumptionRecord> getRecordList() {
		return recordList;
	}

	public String[] getHeaders() {
		return headers;
	}
}
