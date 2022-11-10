package com.ericwang.jaccount;

import java.sql.Connection;
import java.sql.SQLException;

class MyApplicationBackEnd {
    private final MemberRepository memberRepository;

    public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
        System.out.println("connection start");

        ApplicationActor applicationActor = new ApplicationActor();
        applicationActor.start(mySQLConnectionBuilder.getConnection());

        memberRepository = new MemberRepository();
        memberRepository.refresh(applicationActor.getController());
    }

    public void printData(){
        for (Member m : memberRepository.getMemberList())
            System.out.println(m);
    }

    private static class ApplicationActor {
        private final DBController controller;

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
            } catch (SQLException ignored) {
            }
            System.out.println("Query command: " + sql);
        }

        public DBController getController() {
            return controller;
        }
    }
}
