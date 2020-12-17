package com.mysticsanta.controller;

import com.mysticsanta.domain.BindMember;
import com.mysticsanta.domain.Member;
import com.mysticsanta.domain.User;
import com.mysticsanta.repositories.BindMemberRepository;
import com.mysticsanta.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class ContestController {

    public static boolean END_CONTEST = false;

    private static final Random RAND = new Random();

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BindMemberRepository bindMemberRepository;

    @GetMapping("/endContest")
    public String endContest() {
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

                saveBind(memberFrom, bindMember);

                allMembersFrom.remove(memberFrom);
                allMembersTo.remove(memberTo);
            }
        } else {
            BindMember bindMember = new BindMember()
                    .setMemberFrom(allMembersFrom.get(0))
                    .setMemberTo(allMembersFrom.get(0));

            saveBind(allMembersFrom.get(0), bindMember);
        }
        END_CONTEST = true;

        return "redirect:/";
    }

    private void saveBind(Member member, BindMember bindMember) {
        BindMember save = bindMemberRepository.saveAndFlush(bindMember);
        member.setBindMember(save);
        memberRepository.saveAndFlush(member);
    }

    @GetMapping("/getChild")
    public String getPray(@AuthenticationPrincipal User user, Map<String, Object> model) {
        BindMember bindMember = null;
        if (user != null) {
            Member memberByUser = memberRepository.getMemberById(user.getMember().getId());
            bindMember = memberByUser.getBindMember();
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
