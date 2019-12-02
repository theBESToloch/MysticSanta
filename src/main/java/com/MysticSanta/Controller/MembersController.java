package com.MysticSanta.Controller;

import com.MysticSanta.Domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MembersController {

    private List<Member> members = new ArrayList<>();

    @PostMapping("/addMember")
    public String member(@RequestParam String fullName,
                         @RequestParam String key,
                         Map<String, Object> model) {
        members.add(new Member(fullName, key));
        System.out.println(members);
        model.put("members", members);

        return "member";
    }


    @GetMapping("/addMember")
    public String member(Map<String, Object> model) {

        model.put("members", members);

        return "member";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
