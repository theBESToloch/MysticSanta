package com.MysticSanta.Service.Impl;

import com.MysticSanta.Dao.MemberDao;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }

    @Override
    public Long getAllMembersCount() {
        return memberDao.getAllMembersCount();
    }


}
