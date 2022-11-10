package com.ericwang.jaccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            DBConnector dbConnector = new DBConnector("test123");
            System.out.println("connection start");

            DBController controller = new DBController();
            controller.query("SELECT * FROM member", dbConnector.getConnection());

            MemberRepository memberRepository = new MemberRepository();
            memberRepository.init(controller);

            System.out.println(memberRepository.getMemberList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hello world");
    }
}

class MemberRepository {
    private ArrayList<Member> memberList;

    public MemberRepository() {
        memberList = new ArrayList<>();

    }

    public void init(DBController controller) {
        for (int i = 1; i < controller.getRows() + 1; i++) {
            memberList.add(
                    new Member(
                            Integer.parseInt(controller.getData(i, 1)),
                            controller.getData(i, 2),
                            controller.getData(i, 3),
                            controller.getData(i, 4)));
        }
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}