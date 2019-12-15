package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Visitor;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import static com.MysticSanta.Controller.ContestController.END_CONTEST;
import static com.MysticSanta.Utils.Utils.getUserFromSession;

@Controller
public class PagesController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        User user = getUserFromSession();

        if (user != null) {
            model.put("user", user);
            model.put("usersCount", memberService.getAllMembersCount());
            model.put("END_CONTEST", END_CONTEST);
        }

        return "index";
    }

    @Visitor
    @GetMapping("/register")
    public String newUser() {
        return "user/register";
    }

    @Visitor
    @GetMapping("/login")
    public String userAuth() {
        return "user/auth";
    }

    @Authorized
    @GetMapping("/addMember")
    public String member(Map<String, Object> model) {
        User user = getUserFromSession();
        Member member = memberService.getMember(user);

        if (member != null) {
            model.put("wants", member.getWants());
            model.put("notWants", member.getNotWants());
        }

        return "member/member";
    }

    @Authorized
    @GetMapping("/members")
    public String members(Map<String, Object> model) {
        model.put("members", memberService.getAllMembers());

        return "member/members";
    }
}
