package com.ericwang.jaccount;

import com.ericwang.jaccount.backend.CashFlowRecordService;
import com.ericwang.jaccount.backend.ConsumptionCategoryService;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        CashFlowRecordService cashFlowRecordService;
		ConsumptionCategoryService consumptionCategoryService;
        try {
        	MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
        	MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd();
        	
        	myApplicationBackEnd.queryConsumptionCategory(connectionBuilder.getConnection());
        	myApplicationBackEnd.querySingleConsumptionRecord(connectionBuilder.getConnection());
            
            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();
            
            cashFlowRecordService = myApplicationBackEnd.getRawRecordService();
            consumptionCategoryService = myApplicationBackEnd.getConsumptionCategoryService();
            
        } catch (Exception e) {
        	cashFlowRecordService = new CashFlowRecordService();
        	consumptionCategoryService = new ConsumptionCategoryService();
            e.printStackTrace();
        }
        
        MyApplicationFrame myApplicationFrame = new MyApplicationFrame(cashFlowRecordService, consumptionCategoryService);
    }
}