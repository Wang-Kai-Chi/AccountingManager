package com.ericwang.jaccount.backend;

import com.ericwang.jaccount.backend.scr.SingleConsumptionRecord;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrettyConsumptionRecordController {
    private final ArrayList<PrettyConsumptionRecord> recordList;
    private PrettyConsumptionRecordRepository repo;
    private String[] headers;

    public PrettyConsumptionRecordController() {
        recordList = new ArrayList<>();
        headers = new String[2];
    }

    public PrettyConsumptionRecordController(PrettyConsumptionRecordRepository repo) {
        this();
        this.repo = repo;
    }

    public void refresh() {
        if (repo != null) {
            try {
                repo.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            recordList.clear();

            for (int i = 1; i < repo.getRows() + 1; i++) {
                recordList.add(new PrettyConsumptionRecord(
                        Integer.parseInt(repo.getData(i, 1)),
                        Integer.parseInt(repo.getData(i, 2)),
                        repo.getData(i, 3),
                        repo.getData(i, 4),
                        repo.getData(i, 5)));
            }
            headers = repo.getHeaders();
        }
    }
    
    public void insertIntoDb(PrettyConsumptionRecord record, Object[] categories) {
    	SingleConsumptionRecord s = getConsumption(record, categories);
        try {
			repo.add(s);
		} catch (SQLException e) {
			
		}
    }

    public SingleConsumptionRecord getConsumption(PrettyConsumptionRecord p, Object[] categories) {
        SingleConsumptionRecord s = new SingleConsumptionRecord(p.getId());
        s.setAmount_of_money(p.getAmountOfMoney());
        s.setDate(p.getDate());
        s.setCategory(p.getCategory());
        s.setDescription(p.getDescription());

        for (int i = 0; i < categories.length; i++) {
            if (s.getCategory().equals(categories[i]))
                s.setCategory_id(i+1);
        }
        return s;
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
