package com.ll.springproject.controller;

import com.ll.springproject.Rq;
import com.ll.springproject.domain.LoginRes;
import com.ll.springproject.domain.Member;
import com.ll.springproject.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/login")
    public LoginRes login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        if (username == null || username.trim().length() == 0) {
            return LoginRes.of("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return LoginRes.of("F-4", "password(을)를 입력해주세요.");
        }

        LoginRes loginRes = memberService.tryLogin(username, password);

        if(loginRes.getResultCode().equals("S-1"))
            rq.setCookie("username", username);

        return loginRes;

    }
    @GetMapping("/member/logout")
    public LoginRes logout(HttpServletRequest req, HttpServletResponse res) {
        rq.removeCookie("username");


        return LoginRes.of("S-1", "로그아웃 되었습니다.");
    }
    @GetMapping("/member/me")
    public LoginRes me(HttpServletRequest req, HttpServletResponse res){
        String username = rq.getCookie("username");
        if (username == null)
            return LoginRes.of("F-1", "로그인 후 이용해주세요.");


        return LoginRes.of(username + " 님 환영합니다!!", "S-1");


    }


    @GetMapping("/member/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        memberService.signup(username, password);
        return "회원가입에 성공했습니다";
    }
    @GetMapping("/member/selectAll")
    public List<Member> selectAll(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return memberService.selectAll();
    }
}
