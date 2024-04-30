package com.example.DayBridge.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "formdata")
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designId; // 생성 디자인 넘버
    private Long userNo; // 유저 넘버
    private String pointColor; // 포인트 색상
    private String windowPosition; // 창문 위치
    private Integer windowNum; // 창문 개수
    private String essentialFurniture; // 필수 가구
    private String roomSize; // 방 크기
    private String etc; // 기타 세부 사항
}