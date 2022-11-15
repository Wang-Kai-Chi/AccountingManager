package com.ericwang.jaccount.backend;

import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryRepository;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class MyApplicationBackEnd {
    private final ConsumptionCategoryController consumptionCategoryCon;
    private final ConsumptionCategoryRepository consumptionCategoryRepo;

    private final PrettyConsumptionRecordRepository prettyConsumptionRecordRepo;
    private final PrettyConsumptionRecordController prettyConsumptionRecordCon;

    public MyApplicationBackEnd(Connection connection) {
        System.out.println("connection start");

        consumptionCategoryRepo = new ConsumptionCategoryRepository();
        consumptionCategoryRepo.setConnection(connection);
        consumptionCategoryCon = new ConsumptionCategoryController(consumptionCategoryRepo);

        prettyConsumptionRecordRepo = new PrettyConsumptionRecordRepository();
        prettyConsumptionRecordRepo.setConnection(connection);
        prettyConsumptionRecordCon = new PrettyConsumptionRecordController(prettyConsumptionRecordRepo);
    }

	public void refresh() {
        consumptionCategoryCon.refresh();
        prettyConsumptionRecordCon.getFromDb();
    }

    public void printData() {
        consumptionCategoryCon.print();
        prettyConsumptionRecordCon.print();
    }

    public void queryConsumptionCategory() {
        try {
            consumptionCategoryRepo.query();
        } catch (SQLException ignored) {
        }
    }

    public void queryPrettyConsumptionRecord(){
        try {
            prettyConsumptionRecordRepo.query();
        } catch (SQLException ignored) {}
    }

    public ConsumptionCategoryController getConsumptionCategoryCon() {
        return consumptionCategoryCon;
    }

    public PrettyConsumptionRecordController getPrettyConsumptionRecordCon() {
        return prettyConsumptionRecordCon;
    }
}
