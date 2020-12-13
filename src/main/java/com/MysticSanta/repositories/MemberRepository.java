package com.MysticSanta.repositories;

import com.MysticSanta.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
