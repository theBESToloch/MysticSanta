package com.MysticSanta.Dao;

import com.MysticSanta.Domain.Member;

import java.util.List;

public interface MemberDao {
    void addMember(Member member);

    List<Member> getAllMembers();

    long getAllMembersCount();
}
