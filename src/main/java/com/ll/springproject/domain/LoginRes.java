package com.ll.springproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginRes {
    String msg;
    String resultCode;

    public static LoginRes of(String msg, String resultCode) {
        return new LoginRes(msg, resultCode);
    }
}
