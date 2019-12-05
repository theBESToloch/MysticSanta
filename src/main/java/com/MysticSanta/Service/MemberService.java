package com.MysticSanta.Service;

import com.MysticSanta.Domain.Member;

import java.util.List;

public interface MemberService {
    void addMember(Member member);

    List<Member> getAllMembers();

    Long getAllMembersCount();
}
