package com.ericwang.jaccount.backend;

import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryRepository;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordController;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
    private SingleConsumptionRecordController rawRecordCon;
    private SingleConsumptionRecordRepository singleConsumptionRecordRepo;

    private ConsumptionCategoryController consumptionCategoryCon;
    private ConsumptionCategoryRepository consumptionCategoryRepo;

    private PrettyConsumptionRecordRepository prettyConsumptionRecordRepo;
    private PrettyConsumptionRecordController prettyConsumptionRecordCon;

    public MyApplicationBackEnd(Connection connection) {
        System.out.println("connection start");

        singleConsumptionRecordRepo = new SingleConsumptionRecordRepository();
        singleConsumptionRecordRepo.setConnection(connection);
        rawRecordCon = new SingleConsumptionRecordController(singleConsumptionRecordRepo);

        consumptionCategoryRepo = new ConsumptionCategoryRepository();
        consumptionCategoryRepo.setConnection(connection);
        consumptionCategoryCon = new ConsumptionCategoryController(consumptionCategoryRepo);

        prettyConsumptionRecordRepo = new PrettyConsumptionRecordRepository();
        prettyConsumptionRecordRepo.setConnection(connection);
        prettyConsumptionRecordCon = new PrettyConsumptionRecordController(prettyConsumptionRecordRepo);
    }

    public void refresh() {
        rawRecordCon.refresh();
        consumptionCategoryCon.refresh();
        prettyConsumptionRecordCon.refresh();
    }

    public void printData() {
        rawRecordCon.print();
        consumptionCategoryCon.print();
        prettyConsumptionRecordCon.print();
    }

    public void querySingleConsumptionRecord() {
        String sql = "SELECT * FROM single_consumption_record";
        try {
            singleConsumptionRecordRepo.query(sql);
        } catch (SQLException ignored) {
        }
    }

    public void queryConsumptionCategory() {
        String sql2 = "SELECT * FROM `consumption_category`";
        try {
            consumptionCategoryRepo.query(sql2);
        } catch (SQLException ignored) {
        }
    }

    public void queryPrettyConsumptionRecord(){
        String sql = "select consumption.amount_of_money, consumption.date, cc.name as category, consumption.description " +
                "from single_consumption_record as consumption " +
                "join consumption_category as cc " +
                "on consumption.category_id = cc.id;";
        try {
            prettyConsumptionRecordRepo.query(sql);
        } catch (SQLException ignored) {}
    }

    public SingleConsumptionRecordController getRawRecordCon() {
        return rawRecordCon;
    }

    public ConsumptionCategoryController getConsumptionCategoryCon() {
        return consumptionCategoryCon;
    }

    public PrettyConsumptionRecordController getPrettyConsumptionRecordCon() {
        return prettyConsumptionRecordCon;
    }
}
