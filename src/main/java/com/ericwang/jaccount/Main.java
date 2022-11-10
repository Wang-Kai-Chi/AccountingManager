package com.ericwang.jaccount;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            MySQLConnectionBuilder mySQLConnectionBuilder = new MySQLConnectionBuilder("test123");
            System.out.println("connection start");

            DBController controller = new DBController();
            controller.query("SELECT * FROM member", mySQLConnectionBuilder.getConnection());

            //controller.add();
            controller.update(controller.getRows(), "name", "John");
            controller.update(controller.getRows(), "birthday", "1989-10-29");
            controller.update(controller.getRows(), "role", "solder");

            MemberRepository memberRepository = new MemberRepository();
            memberRepository.refresh(controller);

            System.out.println(memberRepository.getMemberList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome to JAccountingManager!!");
    }
}