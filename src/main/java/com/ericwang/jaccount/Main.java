package com.ericwang.jaccount;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");
        try {
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(
                    new MySQLConnectionBuilder("test123"));

            myApplicationBackEnd.printData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}