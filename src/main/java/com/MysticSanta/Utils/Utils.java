package com.MysticSanta.Utils;

import com.MysticSanta.Domain.User;
import com.MysticSanta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Utils {
    public static final String USER = "user";
    public static final String USER_ID = "userId";

    @Autowired
    UserRepository userRepository;

    public void addUserToRequest(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(USER, user);
        Cookie cookie = new Cookie(USER_ID, user.getId().toString());
        cookie.setMaxAge(24 * 60 * 60);
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse()
                .addCookie(cookie);
    }

    public User getUserFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            if (request.getCookies() != null && request.getCookies().length > 0) {
                List<Cookie> cookie1 =
                        Arrays
                                .stream(request.getCookies())
                                .filter(cookie -> cookie.getName().equals(USER_ID))
                                .collect(Collectors.toList());
                if (cookie1.size() > 0) {
                    String userId = cookie1.get(0).getValue();
                    user = userRepository.getOne(userId);
                }
            }
        }
        return user;
    }
}
