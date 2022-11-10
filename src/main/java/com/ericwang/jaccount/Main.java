package com.ericwang.jaccount;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");

        try {
            MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(
                    new MySQLConnectionBuilder("accounting_db01"));

            myApplicationBackEnd.printData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}