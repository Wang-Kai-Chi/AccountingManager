package com.ericwang.jaccount;

import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordController;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        SingleConsumptionRecordController singleConsumptionRecordController;
        ConsumptionCategoryController consumptionCategoryController;
        PrettyConsumptionRecordController prettyConsumptionRecordController;
        try {
            MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(connectionBuilder.getConnection());

            myApplicationBackEnd.queryConsumptionCategory();
            myApplicationBackEnd.querySingleConsumptionRecord();
            myApplicationBackEnd.queryPrettyConsumptionRecord();

            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();

            singleConsumptionRecordController = myApplicationBackEnd.getRawRecordCon();
            consumptionCategoryController = myApplicationBackEnd.getConsumptionCategoryCon();
            prettyConsumptionRecordController = myApplicationBackEnd.getPrettyConsumptionRecordCon();

        } catch (Exception e) {
            singleConsumptionRecordController = new SingleConsumptionRecordController();
            consumptionCategoryController = new ConsumptionCategoryController();
            prettyConsumptionRecordController = new PrettyConsumptionRecordController();
            e.printStackTrace();
        }

        @SuppressWarnings("unused")
        MyApplicationFrame myApplicationFrame =
                new MyApplicationFrame(prettyConsumptionRecordController, consumptionCategoryController);
    }
}