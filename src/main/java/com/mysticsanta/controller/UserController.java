package com.mysticsanta.controller;

import com.mysticsanta.domain.User;
import com.mysticsanta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String newUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            model.put("error", bindingResult.getModel());
            return "user/register";
        }

        if (!userService.saveUser(userForm)) {
            model.put("error", "Пользователь с таким именем уже существует");
            return "user/register";
        }
        System.out.println("New user: " + userForm);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String userAuth(@RequestParam String username, @RequestParam String password) {
        try {
            UserDetails user = userService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

            if (authenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Throwable th) {
            System.out.println(th.getMessage());
        }
        return "redirect:/";
    }
}
