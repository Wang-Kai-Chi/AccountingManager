package com.ericwang.jaccount.backend;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
    private SingleConsumptionRecordController rawRecordCon;
    private SingleConsumptionRecordRepository singleConsumptionRecordRepo;

    private ConsumptionCategoryController consumptionCategoryCon;
    private ConsumptionCategoryRepository consumptionCategoryRepo;

    public MyApplicationBackEnd() {
        System.out.println("connection start");

        singleConsumptionRecordRepo = new SingleConsumptionRecordRepository();
        rawRecordCon = new SingleConsumptionRecordController(singleConsumptionRecordRepo);

        consumptionCategoryRepo = new ConsumptionCategoryRepository();
        consumptionCategoryCon = new ConsumptionCategoryController(consumptionCategoryRepo);
    }

    public void refresh() {
        rawRecordCon.refresh();
        consumptionCategoryCon.refresh();
    }

    public void printData() {
        rawRecordCon.print();
        consumptionCategoryCon.print();
    }

    public void querySingleConsumptionRecord(Connection connection) {
        String sql = "SELECT * FROM single_consumption_record";
        try {
            singleConsumptionRecordRepo.query(sql, connection);
        } catch (SQLException ignored) {
        }
    }

    public void queryConsumptionCategory(Connection connection) {
        String sql2 = "SELECT * FROM `consumption_category`";
        try {
            consumptionCategoryRepo.query(sql2, connection);
        } catch (SQLException ignored) {
        }
    }

    public SingleConsumptionRecordController getRawRecordCon() {
        return rawRecordCon;
    }

    public ConsumptionCategoryController getConsumptionCategoryCon() {
        return consumptionCategoryCon;
    }
}
