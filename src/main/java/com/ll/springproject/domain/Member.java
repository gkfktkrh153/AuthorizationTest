package com.ll.springproject.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Member {

    private static Long lastId = 0L;

    @Id // PRIMARY KEY
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column
    private String name;
    @Column
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
