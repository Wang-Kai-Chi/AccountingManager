package com.ericwang.jaccount;

import java.sql.SQLException;

import com.ericwang.jaccount.backend.CashFlowRecordRepository;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        CashFlowRecordRepository repo;
        try {
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(
                    new MySQLConnectionBuilder("accounting_db01"));
            
            myApplicationBackEnd.refresh();
            myApplicationBackEnd.printData();
            
            repo = myApplicationBackEnd.getCashFlowRecordRepository();
            
        } catch (Exception e) {
        	repo = new CashFlowRecordRepository();
            e.printStackTrace();
        }
        
        MyApplicationFrame myApplicationFrame = new MyApplicationFrame(repo);
    }
}