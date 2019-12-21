package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.MemberService;
import com.MysticSanta.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MembersController {

    @Autowired
    MemberService memberService;

    @Autowired
    Utils utils;

    @Authorized
    @PostMapping("/addMember")
    public String addMember(@RequestParam String wants, @RequestParam String notWants) {
        User user = utils.getUserFromRequest();
        Member member = new Member(wants, notWants, user);
        memberService.addMember(member);
        System.out.println("new member ip = " + user.getUserId());

        return ("redirect:/");
    }

    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/delMember/{id}")
    public String deleteMember(@PathVariable("id") String id) {
        memberService.deleteMember(
                (User) memberService
                        .getAllMembers()
                        .stream()
                        .filter(member -> member.getUser().getUserId().equals(id))
                        .map(Member::getUser)
                        .toArray()[0]);

        return "redirect:/members";
    }


}
