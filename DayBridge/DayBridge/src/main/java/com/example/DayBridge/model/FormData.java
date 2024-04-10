package com.example.DayBridge.model;

import javax.persistence.*;

@Entity
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

    // Getters and setters

    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getPointColor() {
        return pointColor;
    }

    public void setPointColor(String pointColor) {
        this.pointColor = pointColor;
    }

    public String getWindowPosition() {
        return windowPosition;
    }

    public void setWindowPosition(String windowPosition) {
        this.windowPosition = windowPosition;
    }

    public Integer getWindowNum() {
        return windowNum;
    }

    public void setWindowNum(Integer windowNum) {
        this.windowNum = windowNum;
    }

    public String getEssentialFurniture() {
        return essentialFurniture;
    }

    public void setEssentialFurniture(String essentialFurniture) {
        this.essentialFurniture = essentialFurniture;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public void getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}