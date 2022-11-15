package com.ericwang.jaccount;


import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.backend.cc.ConsumptionCategory;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        ConsumptionCategoryController consumptionCategoryController;
        PrettyConsumptionRecordController prettyConsumptionRecordController;
        try {
            MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(connectionBuilder.getConnection());

            myApplicationBackEnd.queryConsumptionCategory();
            myApplicationBackEnd.queryPrettyConsumptionRecord();

            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();

            consumptionCategoryController = myApplicationBackEnd.getConsumptionCategoryCon();
            prettyConsumptionRecordController = myApplicationBackEnd.getPrettyConsumptionRecordCon();

        } catch (Exception e) {
            consumptionCategoryController = new ConsumptionCategoryController();
            prettyConsumptionRecordController = new PrettyConsumptionRecordController();
            e.printStackTrace();
        }
        ArrayList<String> categories = new ArrayList<>();

		for (ConsumptionCategory c : consumptionCategoryController.getRecordList())
			categories.add(c.getName());

		@SuppressWarnings("unused")
        MyApplicationFrame myApplicationFrame =
                new MyApplicationFrame(prettyConsumptionRecordController, categories.toArray());
    }
}