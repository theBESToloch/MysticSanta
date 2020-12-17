package com.mysticsanta.controller;

import com.mysticsanta.domain.Member;
import com.mysticsanta.domain.User;
import com.mysticsanta.repositories.MemberRepository;
import com.mysticsanta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MembersController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addMember")
    public String addMember(@AuthenticationPrincipal User user, @RequestParam String wants, @RequestParam String notWants) {
        Member member =
                Optional.ofNullable(user)
                        .map(User::getMember)
                        .map(Member::getId)
                        .map(id -> memberRepository.getMemberById(id))
                        .orElse(null);
        if (member == null) {
            member = new Member(wants, notWants);
            member.setUser(user);
            user.setMember(member);
        } else {
            member.setWants(wants);
            member.setNotWants(notWants);
        }
        member = memberRepository.saveAndFlush(member);
        user.setMember(member);

        user = userRepository.saveAndFlush(user);

        System.out.println("new member ip = " + user.getId());
        return ("redirect:/");
    }

    @GetMapping("/delMember/{id}")
    public String deleteMember(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        user.ifPresent(value -> memberRepository.delete(value.getMember()));
        return "redirect:/members";
    }
}
