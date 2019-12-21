package com.MysticSanta.Dao;

import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.User;

import java.util.List;

public interface MemberDao {
    void addMember(Member member);

    List<Member> getAllMembers();

    long getAllMembersCount();

    Member getMember(User user);

    void deleteMember(User user);
}
