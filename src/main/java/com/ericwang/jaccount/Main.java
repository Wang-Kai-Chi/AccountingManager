package com.ericwang.jaccount;

import com.ericwang.jaccount.backend.SingleConsumptionRecordController;
import com.ericwang.jaccount.backend.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        SingleConsumptionRecordController singleConsumptionRecordController;
        ConsumptionCategoryController consumptionCategoryController;
        try {
            MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd();

            myApplicationBackEnd.queryConsumptionCategory(connectionBuilder.getConnection());
            myApplicationBackEnd.querySingleConsumptionRecord(connectionBuilder.getConnection());

            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();

            singleConsumptionRecordController = myApplicationBackEnd.getRawRecordCon();
            consumptionCategoryController = myApplicationBackEnd.getConsumptionCategoryCon();

        } catch (Exception e) {
            singleConsumptionRecordController = new SingleConsumptionRecordController();
            consumptionCategoryController = new ConsumptionCategoryController();
            e.printStackTrace();
        }

        @SuppressWarnings("unused")
        MyApplicationFrame myApplicationFrame =
                new MyApplicationFrame(singleConsumptionRecordController, consumptionCategoryController);
    }
}