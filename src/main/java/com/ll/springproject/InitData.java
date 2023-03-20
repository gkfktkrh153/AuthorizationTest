package com.ll.springproject;


import com.ll.springproject.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev","test"}) // 개발환경이거나 테스트 환경일 때만 실행
public class InitData {

    @Bean
    public CommandLineRunner init(MemberService memberService){
        return args -> {
        memberService.signup("user1", "1234");
        memberService.signup("abc", "12345");
        memberService.signup("test", "12346");
        memberService.signup("love", "12347");
        memberService.signup("like", "12348");
        memberService.signup("giving", "12349");
        memberService.signup("thanks", "123410");
        memberService.signup("hello", "123411");
        memberService.signup("good", "123412");
        memberService.signup("peace", "123413");
        };
    }
}
