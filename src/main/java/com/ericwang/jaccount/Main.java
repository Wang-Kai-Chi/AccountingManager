package com.ericwang.jaccount;

import com.ericwang.jaccount.backend.CashFlowRecordService;
import com.ericwang.jaccount.backend.ConsumptionCategoryService;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        CashFlowRecordService service;
        ConsumptionCategoryService service2;
        try {
        	MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(
                    new MySQLConnectionBuilder("xx"));
            
            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();
            
            service = myApplicationBackEnd.getCashFlowRecordService();
            service2 = myApplicationBackEnd.getConsumptionCategoryService();
            
        } catch (Exception e) {
        	service = new CashFlowRecordService();
        	service2 = new ConsumptionCategoryService();
            e.printStackTrace();
        }
        
        MyApplicationFrame myApplicationFrame = new MyApplicationFrame(service, service2);
    }
}