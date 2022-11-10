package com.ericwang.jaccount;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            MySQLConnectionBuilder mySQLConnectionBuilder = new MySQLConnectionBuilder("test123");
            System.out.println("connection start");

            DBController controller = new DBController();
            controller.query("SELECT * FROM member", mySQLConnectionBuilder.getConnection());

            MemberRepository memberRepository = new MemberRepository();
            memberRepository.init(controller);

            System.out.println(memberRepository.getMemberList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome to JAccountingManager!!");
    }
}