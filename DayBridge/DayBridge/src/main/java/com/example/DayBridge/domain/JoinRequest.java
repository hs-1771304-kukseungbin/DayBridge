package com.example.DayBridge.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    private String userID;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPW;
    private String pwCheck;

    @NotBlank(message = "사용하실 닉네임을 입력해주세요")
    private String nickName;

    public Users toEntity(String encode) {
        return Users.builder()
                .userID(this.userID)
                .userPW(encode)
                .nickName(this.nickName)
                .build();
    }
}
