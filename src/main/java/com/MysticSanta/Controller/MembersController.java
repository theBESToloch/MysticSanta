package com.MysticSanta.Controller;

import com.MysticSanta.Domain.Member;
import com.MysticSanta.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MembersController {

    @Autowired
    MemberService memberService;



    @PostMapping("/addMember")
    public String member(@RequestParam String fullName,
                         @RequestParam String key,
                         Map<String, Object> model) {
        memberService.addMember(new Member(fullName, key));

        List<Member> members = memberService.getAllMembers();
        model.put("members", members);

        return "member";
    }


    @GetMapping("/addMember")
    public String member(Map<String, Object> model) {
        List<Member> members = memberService.getAllMembers();

        model.put("members", members);
        return "member";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
