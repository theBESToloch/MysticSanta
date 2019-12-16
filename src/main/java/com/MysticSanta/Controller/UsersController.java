package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Visitor;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.MemberService;
import com.MysticSanta.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static com.MysticSanta.Utils.Utils.addUserToSession;
import static com.MysticSanta.Utils.Utils.getUserFromSession;

@Controller
public class UsersController {

    public static final String ERROR = "error";

    @Autowired
    MemberService memberService;

    @Autowired
    UserService userService;

    @Visitor
    @PostMapping("/register")
    public String newUser(String firstName, String lastName, Map<String, Object> model) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            model.put(ERROR, "1");
            return "user/register";
        }
        User user = new User(firstName, lastName);

        userService.addNewUser(user);
        addUserToSession(user);

        System.out.println("New user: " + user);
        return "redirect:/";
    }

    @Visitor
    @PostMapping("/login")
    public String userAuth(String id, Map<String, Object> model) {
        User user = userService.getUser(id);
        if (user != null) {
            addUserToSession(user);
        } else {
            model.put(ERROR, "1");
            return "user/auth";
        }
        return "redirect:/";
    }
}
