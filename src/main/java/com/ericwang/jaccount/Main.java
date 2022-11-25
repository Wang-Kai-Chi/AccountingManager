package com.ericwang.jaccount;


import com.ericwang.jaccount.backend.ApplicationBackEnd;
import com.ericwang.jaccount.backend.ControllerService;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.backend.SQLCInitializer;
import com.ericwang.jaccount.backend.SQLCommand;
import com.ericwang.jaccount.backend.cc.ConsumptionCategory;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        ApplicationBackEnd backEnd = new ApplicationBackEnd();

		@SuppressWarnings("unused")
        MyApplicationFrame myApplicationFrame =
                new MyApplicationFrame(
                		backEnd.getPrettyConsumptionRecordController(), 
                		backEnd.getCategories());
    }
}