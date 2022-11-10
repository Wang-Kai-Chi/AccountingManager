package com.ericwang.jaccount;

import java.util.ArrayList;

public class CashFlowRecordRepository {
    private ArrayList<CashFlowRecord> recordList;

    public CashFlowRecordRepository(){
        recordList = new ArrayList<>();
    }

    public void refresh(){
        recordList.clear();

        recordList.add(new CashFlowRecord(
                1,
                100,
                "2022-11-01",
                1));
        recordList.add(new CashFlowRecord(
                2,
                150,
                "2022-11-01",
                1));
        recordList.add(new CashFlowRecord(
                3,
                80,
                "2022-11-02",
                2));
        recordList.add(new CashFlowRecord(
                4,
                120,
                "2022-11-02",
                2));
        recordList.add(new CashFlowRecord(
                5,
                180,
                "2022-11-02",
                3));
        recordList.add(new CashFlowRecord(
                6,
                180,
                "2022-11-03",
                1));
    }

    public ArrayList<CashFlowRecord> getRecordList() {
        return recordList;
    }
}
