package com.ll.springproject;

import com.ll.springproject.domain.LoginRes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Getter
@Component
@AllArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse res;

    public boolean removeCookie(String name) {
        if (req.getCookies() != null) {
            Cookie cookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);

            if (cookie != null) {
                cookie.setMaxAge(0);
                res.addCookie(cookie);

                return true;
            }
        }

        return false;
    }

    public String getCookie(String name) {
        if (req.getCookies() == null) return null;

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }


    public void setCookie(String name, long value) {
        setCookie(name, value + "");
    }

    public void setCookie(String name, String value) {
        res.addCookie(new Cookie(name, value));
    }
    public void setSession(String name, String value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }
    public String getSession(String name, String defaultValue) {
        try {
            String value = (String) req.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
    public boolean isLogined() {
        String username = getSession("username","null");
        return !username.equals("null");
    }

    public boolean isLogout() {
        return !isLogined();
    }
}
