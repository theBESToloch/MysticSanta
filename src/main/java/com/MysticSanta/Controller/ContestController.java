package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.MemberService;
import com.MysticSanta.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.MysticSanta.Utils.Utils.getUserFromSession;

@Controller
public class ContestController {

    public static boolean END_CONTEST = false;

    public static Map<User, Member> YOUR_CHILD = new HashMap<>();

    @Autowired
    MemberService memberService;

    @Autowired
    UserService userService;

    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/endContest")
    public String endContest() {
        List<Member> allMembers = memberService.getAllMembers();
        List<User> allUser = userService.getAllUser();

        if (allUser.size() > 1) {
            while (allMembers.size() > 0) {
                User user = getRandomObj(allUser);
                Member member = getRandomObj(allMembers);
                while (user.equals(member.getUser())) {
                    member = getRandomObj(allMembers);
                }
                YOUR_CHILD.put(user, member);
                allUser.remove(user);
                allMembers.remove(member);
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
        User user = getUserFromSession();
        model.put("child", YOUR_CHILD.get(user));

        return "viewChild";
    }


    private <T> T getRandomObj(List<T> list) {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }
}
