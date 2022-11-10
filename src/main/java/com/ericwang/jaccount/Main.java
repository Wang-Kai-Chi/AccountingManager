package com.ericwang.jaccount;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JAccountingManager!!");
        try {
            MyApplication myApplication = new MyApplication(new MySQLConnectionBuilder("test123"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class MyApplication {
    public MyApplication(MySQLConnectionBuilder mySQLConnectionBuilder) {
        System.out.println("connection start");

        ApplicationActor applicationActor = new ApplicationActor();
        applicationActor.start(mySQLConnectionBuilder.getConnection());

        MemberRepository memberRepository = new MemberRepository();
        memberRepository.refresh(applicationActor.getController());

        for (Member m : memberRepository.getMemberList())
            System.out.println(m);
    }
}

class ApplicationActor {
    private DBController controller;

    public ApplicationActor() {
        controller = new DBController();
    }

    public void start(Connection connection) {
        String sql = "SELECT * FROM member";
        try {
            controller.query(sql, connection);
        //controller.add();

//            controller.update(controller.getRows(), controller.getHeaders()[1], "John");
//            controller.update(controller.getRows(), controller.getHeaders()[2], "1989-10-29");
//            controller.update(controller.getRows(), controller.getHeaders()[3], "solder");
        } catch (SQLException ignored) {}
        System.out.println("Query command: " + sql);
    }

    public DBController getController() {
        return controller;
    }
}