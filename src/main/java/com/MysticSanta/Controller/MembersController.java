package com.MysticSanta.Controller;

import com.MysticSanta.Domain.Member;
import com.MysticSanta.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.MysticSanta.Utils.Utils.getClientIpAddressIfServletRequestExist;

@Controller
public class MembersController {


    @Autowired
    MemberService memberService;

    @PostMapping("/addMember")
    public String member(@RequestParam String fullName) {

        Member member = new Member(fullName, getClientIpAddressIfServletRequestExist());

        int index = memberService.getAllMembers().indexOf(member);

        if (index != -1) {
            memberService.getAllMembers().remove(index);
        }
        System.out.println("new member ip = " + member.getIp());
        memberService.addMember(member);

        return ("redirect:/");
    }

    @GetMapping("/addMember")
    public String member() {
        return "member";
    }

}
