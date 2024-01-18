package com.hello.hellospring.domain;

import jakarta.persistence.*;

@Entity // JPA과 관리하는 Entity임을 선언
public class Member {
    /**
     * @Id : PK
     * @GeneratedValue(strategy = GenerationType.IDENTITY) : DB가 ID를 자동생성해주는 전략을 Identity라고 한다.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(name = "username") : DB 테이블 컬럼 명이 username이라면 이렇게 한다.
     */
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}