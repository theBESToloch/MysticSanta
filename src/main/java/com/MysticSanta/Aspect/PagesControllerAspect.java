package com.MysticSanta.Aspect;

import com.MysticSanta.Anntotation.Roles;
import com.MysticSanta.Domain.Role;
import com.MysticSanta.Domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.MysticSanta.Utils.Utils.getUserFromSession;

@Component
@Aspect
public class PagesControllerAspect {

    @Pointcut("execution(public * com.MysticSanta.Controller.PagesController+.*(..))")
    public void callAtPagesController() {
    }

    @Pointcut("@annotation(com.MysticSanta.Anntotation.Authorized)")
    public void callAuthorizedUser() {
    }

    @Pointcut("@annotation(com.MysticSanta.Anntotation.Visitor)")
    public void callVisitor() {
    }

    @Pointcut("@annotation(com.MysticSanta.Anntotation.Roles)")
    public void callWithRole() {
    }

    @Around("callAuthorizedUser()")
    public Object doAccessAuthorizedUserCheck(ProceedingJoinPoint pjp) throws Throwable {
        User user = getUserFromSession();
        if (user == null) {
            return "redirect:/";
        }
        return pjp.proceed();
    }

    @Around("callVisitor()")
    public Object doAccessVisitorCheck(ProceedingJoinPoint pjp) throws Throwable {
        User user = getUserFromSession();
        if (user == null) {
            return pjp.proceed();
        }
        return "redirect:/";
    }

    @Around("callWithRole()")
    public Object doAccessRolesCheck(ProceedingJoinPoint pjp) throws Throwable {
        User user = getUserFromSession();
        if (user == null) {
            return "redirect:/";
        }
        Method method = Arrays.stream(pjp.getTarget().getClass().getDeclaredMethods())
                .filter(
                        m -> m
                                .getName()
                                .equals(pjp.getStaticPart().getSignature().getName()))
                .findFirst()
                .orElse(null);
        if (method != null) {
            Roles roles = method.getAnnotation(Roles.class);
            Role[] value = roles.value();
            if (user.getRole().stream().anyMatch(role -> Arrays.asList(value).contains(role))) {
                return pjp.proceed();
            }
        }
        return "redirect:/";
    }
}
