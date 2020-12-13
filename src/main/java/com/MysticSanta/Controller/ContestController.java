package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Authorized;
import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.BindMember;
import com.MysticSanta.Domain.Member;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Utils.Utils;
import com.MysticSanta.repositories.BindMemberRepository;
import com.MysticSanta.repositories.MemberRepository;
import com.MysticSanta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Controller
public class ContestController {

    public static boolean END_CONTEST = false;

    private static final Random RAND = new Random();

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BindMemberRepository bindMemberRepository;
    @Autowired
    private Utils utils;

    @Authorized
    @Roles(Role.ADMIN)
    @GetMapping("/endContest")
    public String endContest() {

        if (END_CONTEST) {
            return "redirect:/";
        }

        List<Member> allMembersFrom = memberRepository.findAll();
        if (allMembersFrom.size() > 1) {
            List<Member> allMembersTo = new ArrayList<>(allMembersFrom);
            while (allMembersFrom.size() > 0) {
                Member memberFrom = getRandomObj(allMembersFrom);

                Member memberTo;
                while ((memberTo = getRandomObj(allMembersTo)).equals(memberFrom)) ;

                BindMember bindMember = new BindMember()
                        .setMemberFrom(memberFrom)
                        .setMemberTo(memberTo);

                BindMember save = bindMemberRepository.saveAndFlush(bindMember);
                memberFrom.setBindMember(save);
                memberRepository.saveAndFlush(memberFrom);

                allMembersFrom.remove(memberFrom);
                allMembersTo.remove(memberTo);
            }
        } else {
            bindMemberRepository.save(new BindMember().setMemberFrom(allMembersFrom.get(0)).setMemberTo(allMembersFrom.get(0)));
        }
        END_CONTEST = true;

        return "redirect:/";
    }

    @Authorized
    @GetMapping("/getChild")
    public String getPray(Map<String, Object> model) {
        Long userId = utils.getUserId();
        Optional<User> user = userRepository.findById(userId);
        BindMember bindMember = null;
        if (user.isPresent()) {
            utils.addUserToRequest(user.get());
            bindMember = user.get().getMember().getBindMember();
        }

        if (bindMember == null) {
            model.put("error", "Вас не в списке участников либо вы не попали в рандомизацию");
        } else {
            model.put("error", null);
            model.put("child", bindMember.getMemberTo());
        }
        return "viewChild";
    }

    private <T> T getRandomObj(List<T> list) {
        int index = RAND.nextInt(list.size());
        return list.get(index);
    }
}
