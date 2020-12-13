package com.MysticSanta.Controller;

import com.MysticSanta.Anntotation.Visitor;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Utils.Utils;
import com.MysticSanta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class UsersController {

    public static final String ERROR = "error";

    @Autowired
    Utils utils;

    @Autowired
    UserRepository userRepository;

    @Visitor
    @PostMapping("/register")
    public String newUser(String firstName, String lastName, Map<String, Object> model) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            model.put(ERROR, "1");
            return "user/register";
        }
        User user = new User(firstName, lastName);
        user = userRepository.saveAndFlush(user);

        utils.addUserToRequest(user);

        System.out.println("New user: " + user);
        return "redirect:/";
    }

    @Visitor
    @PostMapping("/login")
    public String userAuth(String id, Map<String, Object> model) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            utils.addUserToRequest(user.get());
        } else {
            model.put(ERROR, "1");
            return "user/auth";
        }
        return "redirect:/";
    }
}
