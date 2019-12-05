package com.MysticSanta.Controller;

import com.MysticSanta.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public class UtilsController {

    @Autowired
    MemberService memberService;

    @Autowired


    @RequestMapping("/endContest")
    public String endContest(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("usersCount", memberService.getAllMembersCount());
        return "index";
    }
}
