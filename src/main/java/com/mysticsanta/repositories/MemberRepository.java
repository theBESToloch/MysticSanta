package com.mysticsanta.repositories;

import com.mysticsanta.domain.Member;
import com.mysticsanta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member getMemberById(Long memberId);

}
