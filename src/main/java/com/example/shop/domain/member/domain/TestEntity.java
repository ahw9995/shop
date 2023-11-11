package com.example.shop.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestEntity {

    @Id
    private String id;

    private String name;

    private String nickname;

}
