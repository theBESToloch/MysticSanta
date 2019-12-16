package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.MemberService;
import com.MysticSanta.Service.UserService;
import com.MysticSanta.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ContestController {

    public static boolean END_CONTEST = false;

    public static Map<User, Member> YOUR_CHILD = new HashMap<>();

    @Autowired
    MemberService memberService;

    @Autowired
    UserService userService;

    @Autowired
    Utils utils;


    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/endContest")
    public String endContest() {
        YOUR_CHILD.clear();
        List<Member> allMembers = new ArrayList<>(memberService.getAllMembers());
        List<User> allUser = new ArrayList<>(userService.getAllUser());

        allUser.removeIf(user -> allMembers.stream().noneMatch(member -> member.getUser().equals(user)));

        if (allUser.size() > 1) {
            while (allUser.size() > 0) {
                int userIndex = getRandomObj(allUser);
                int memberIndex = getRandomObj(allMembers);
                while (allUser.get(userIndex).equals(allMembers.get(memberIndex).getUser())) {
                    memberIndex = getRandomObj(allMembers);
                }
                YOUR_CHILD.put(allUser.get(userIndex), allMembers.get(memberIndex));
                allMembers.remove(memberIndex);
                allUser.remove(userIndex);

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
        model.put("child", YOUR_CHILD.get(user));

        return "viewChild";
    }


    private <T> int getRandomObj(List<T> list) {
        return new Random().nextInt(list.size());
    }
}
