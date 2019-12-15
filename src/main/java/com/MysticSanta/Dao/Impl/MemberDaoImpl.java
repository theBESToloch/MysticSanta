package com.MysticSanta.Dao.Impl;


import com.MysticSanta.Dao.MemberDao;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemberDaoImpl implements MemberDao {

    private Map<User, Member> members = new HashMap<>();

    @Override
    public void addMember(Member member) {
        members.put(member.getUser(), member);
    }

    @Override
    public List<Member> getAllMembers() {
        return members.keySet().stream().map((key) -> members.get(key)).collect(Collectors.toList());
    }

    @Override
    public long getAllMembersCount() {
        return members.size();
    }

    @Override
    public Member getMember(User user) {
        return members.get(user);
    }

    @Override
    public void deleteMember(User user) {
        members.remove(user);
    }
}
