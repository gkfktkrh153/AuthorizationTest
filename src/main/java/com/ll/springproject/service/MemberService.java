package com.ll.springproject.service;

import com.ll.springproject.domain.LoginRes;
import com.ll.springproject.domain.Member;
import com.ll.springproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public LoginRes tryLogin(String username, String password)
    {
        Member member = memberRepository.findByUsername(username);
        if (username.equals(member.getName())){
            if(password.equals(member.getPassword())){
                return  LoginRes.of(member.getName() +" 님 환영합니다","S-1");
            }
            else {
                return LoginRes.of("비밀번호가 일치하지 않습니다.","F-1");
            }
        }
        else{
            return LoginRes.of(member.getName() + " 는 존재하지 않는 회원입니다","F-2");
        }

    }
    public void signup(String username, String password){
        memberRepository.save(username, password);

    }
    public List<Member> selectAll(){
        List<Member> all = memberRepository.getAll();
        return all;
    }
}
