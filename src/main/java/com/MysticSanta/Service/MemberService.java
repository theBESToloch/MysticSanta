package com.MysticSanta.Service;

import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.User;

import java.util.List;

public interface MemberService {
    void addMember(Member member);

    Member getMember(User user);

    List<Member> getAllMembers();

    Long getAllMembersCount();

    void deleteMember(User user);
}
