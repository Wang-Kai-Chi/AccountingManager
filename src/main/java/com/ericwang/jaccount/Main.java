package com.ericwang.jaccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            DBConnector dbConnector = new DBConnector("test123");
            System.out.println("connection start");

            dbConnector.query("SELECT * FROM member");

            MemberRepository memberRepository = new MemberRepository();
            memberRepository.init(dbConnector);

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

    public void init(DBConnector dbConnector) {
        for (int i = 1; i < dbConnector.getRows() + 1; i++) {
            memberList.add(
                    new Member(
                            Integer.parseInt(dbConnector.getData(i, 1)),
                            dbConnector.getData(i, 2),
                            dbConnector.getData(i, 3),
                            dbConnector.getData(i, 4)));
        }
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}