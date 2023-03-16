package com.ll.springproject.repository;

import com.ll.springproject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    List<Member> memberRepository  = new ArrayList<>();

    public String save(String username, String password){
        memberRepository.add(Member.of(username,password));
        return "저장에 성공했습니다";
    }

    public List<Member> getAll() {
        return memberRepository;
    }

    public Member findByUsername(String username) {
        return memberRepository.stream().filter(m -> m.getName().equals(username))
                .findFirst()
                .orElse(null);


    }
}
