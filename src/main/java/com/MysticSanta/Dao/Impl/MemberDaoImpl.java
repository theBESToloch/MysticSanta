package com.MysticSanta.Dao.Impl;


import com.MysticSanta.Dao.MemberDao;
import com.MysticSanta.Domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    private List<Member> members = new ArrayList<>();

    @Override
    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return members;
    }
}
