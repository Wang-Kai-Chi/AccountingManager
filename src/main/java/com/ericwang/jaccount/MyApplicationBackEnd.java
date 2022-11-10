package com.ericwang.jaccount;

import java.sql.Connection;
import java.sql.SQLException;

class MyApplicationBackEnd {
    private final CashFlowRecordRepository cashFlowRecordRepository;

    public MyApplicationBackEnd(MySQLConnectionBuilder mySQLConnectionBuilder) {
        System.out.println("connection start");

        ApplicationActor applicationActor = new ApplicationActor();
        applicationActor.start(mySQLConnectionBuilder.getConnection());

        cashFlowRecordRepository = new CashFlowRecordRepository(applicationActor.getController());
        cashFlowRecordRepository.refresh();
    }

    public void printData(){
        for (CashFlowRecord c : cashFlowRecordRepository.getRecordList())
            System.out.println(c);
    }

    private static class ApplicationActor {
        private final DBController controller;

        public ApplicationActor() {
            controller = new DBController();
        }

        public void start(Connection connection) {
            String sql = "SELECT * FROM cash_flow_record";
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
