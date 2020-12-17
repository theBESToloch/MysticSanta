package com.mysticsanta.controller;

import com.mysticsanta.domain.Member;
import com.mysticsanta.domain.User;
import com.mysticsanta.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static com.mysticsanta.controller.ContestController.END_CONTEST;

@Controller
public class PagesController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails user, Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (user != null) {
            model.put("user", user);
            model.put("usersCount", memberRepository.count());
            model.put("END_CONTEST", END_CONTEST);
        }

        return "index";
    }

    @GetMapping("/register")
    public String newUser() {
        return "user/register";
    }

    @GetMapping("/login")
    public String userAuth() {
        return "user/login";
    }

    @GetMapping("/addMember")
    public String member(@AuthenticationPrincipal User user, Map<String, Object> model) {
        Member member = user.getMember();

        if (member != null) {
            model.put("wants", member.getWants());
            model.put("notWants", member.getNotWants());
        }

        return "member/member";
    }

    @GetMapping("/members")
    public String members(Map<String, Object> model) {
        List<Member> all = memberRepository.findAll();
        model.put("members", all);
        return "member/members";
    }
}
