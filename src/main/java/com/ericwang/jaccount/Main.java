package com.ericwang.jaccount;

import com.ericwang.jaccount.backend.CashFlowRecordService;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        CashFlowRecordService service;
        try {
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(
                    new MySQLConnectionBuilder("accounting_db01"));
            
            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();
            
            service = myApplicationBackEnd.getCashFlowRecordService();
            
        } catch (Exception e) {
        	service = new CashFlowRecordService();
            e.printStackTrace();
        }
        
        MyApplicationFrame myApplicationFrame = new MyApplicationFrame(service);
    }
}