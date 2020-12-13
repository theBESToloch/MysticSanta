package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Utils.Utils;
import com.MysticSanta.repositories.MemberRepository;
import com.MysticSanta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Utils utils;

    @Authorized
    @PostMapping("/addMember")
    public String addMember(@RequestParam String wants, @RequestParam String notWants) {
        User user = utils.getUserFromRequest();
        Member member = user.getMember();
        if (member == null) {
            member = new Member(wants, notWants);
            member.setUser(user);
            user.setMember(member);
        } else {
            member.setUser(user);
            member.setWants(wants);
            member.setNotWants(notWants);
        }
        member = memberRepository.saveAndFlush(member);
        user.setMember(member);

        user = userRepository.saveAndFlush(user);

        utils.addUserToRequest(user);
        System.out.println("new member ip = " + user.getId());
        return ("redirect:/");
    }

    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/delMember/{id}")
    public String deleteMember(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> memberRepository.delete(value.getMember()));
        return "redirect:/members";
    }
}
