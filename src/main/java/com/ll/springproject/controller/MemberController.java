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
    public String showLogin() {
        if (rq.isLogined()) {
            return """
                    <h1>이미 로그인 되었습니다.</h1>
                    """.stripIndent();
        }

        return """
                <h1>로그인</h1>
                <form action="doLogin">
                <input type="text" placeholder="아이디" name="username">
                <input type="password" placeholder="비번호" name="password">
                <input type="submit" value="로그인">
                </form>
                """;
    }

    @GetMapping("/member/doLogin")
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
            rq.setSession("username", username);

        return loginRes;

    }
    @GetMapping("/member/logout")
    public LoginRes logout(HttpServletRequest req, HttpServletResponse res) {
        boolean cookieRemoved = rq.isLogout();

        if (cookieRemoved == false) {
            return LoginRes.of("S-2", "이미 로그아웃 상태입니다.");
        }

        return LoginRes.of("S-1", "로그아웃 되었습니다.");
    }
    @GetMapping("/member/me")
    public LoginRes me(HttpServletRequest req, HttpServletResponse res){
        String username = rq.getSession("username","null");
        if (username.equals("null"))
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
