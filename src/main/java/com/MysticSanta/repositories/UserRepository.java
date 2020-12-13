package com.MysticSanta.repositories;

import com.MysticSanta.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
