package com.ericwang.jaccount;

import java.util.ArrayList;

class MemberRepository {
    private final ArrayList<Member> memberList;

    public MemberRepository() {
        memberList = new ArrayList<>();
    }

    public void refresh(DBController controller) {
        memberList.clear();

        for (int i = 1; i < controller.getRows() + 1; i++) {
            memberList.add(
                    new Member(
                            Integer.parseInt(controller.getData(i, 1)),
                            controller.getData(i, 2),
                            controller.getData(i, 3),
                            controller.getData(i, 4))
            );
        }
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}
