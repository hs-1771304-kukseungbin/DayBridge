package com.example.DayBridge.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Users {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int age;
    private String nickName;
    private String userID;
    private String userPW;

}
