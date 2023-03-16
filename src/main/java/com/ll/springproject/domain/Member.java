package com.ll.springproject.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Member {

    private static Long lastId = 0L;
    private Long id;
    private String name;
    private String password;

    public static Member of(String name, String password){
        return new Member(name, password);
    }
    public Member(String name, String password) {
        this.id = lastId++;
        this.name = name;
        this.password = password;
    }
}
