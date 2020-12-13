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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContestController {

    public static boolean END_CONTEST = false;

    public static Map<User, Member> YOUR_CHILD = new HashMap<>();

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;


    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/endContest")
    public String endContest() {
        YOUR_CHILD.clear();
        List<Member> allMembers = memberRepository.findAll();
        List<User> allUser = userRepository.findAll();

        allUser.removeIf(user -> allMembers.stream().noneMatch(member -> user.getMember().equals(member)));

        if (allUser.size() > 1) {
            Collections.shuffle(allMembers);
            Collections.shuffle(allUser);

            for (int i = 0; i < allMembers.size(); i++) {
                YOUR_CHILD.put(allUser.get(i), allMembers.get(i));
            }
        } else {
            YOUR_CHILD.put(allUser.get(0), allMembers.get(0));
        }
        END_CONTEST = true;

        return "redirect:/";
    }

    @Authorized
    @GetMapping("/getChild")
    public String getPray(Map<String, Object> model) {
        User user = utils.getUserFromRequest();
        Member member = YOUR_CHILD.get(user);
        if (member == null) {
            model.put("error", "Вас не в списке участников либо вы не попали в рандомизацию");
        } else {
            model.put("error", null);
            model.put("child", member);
        }
        return "viewChild";
    }
}
