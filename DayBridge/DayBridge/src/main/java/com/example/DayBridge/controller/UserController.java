package com.example.DayBridge.controller;

import com.example.DayBridge.domain.JoinRequest;
import com.example.DayBridge.domain.LoginRequest;
import com.example.DayBridge.domain.Users;
import com.example.DayBridge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/DayBridge")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        if(auth != null) {
            Users loginUser = userService.getLoginUserByID(auth.getName());
            if(loginUser != null) {
                model.addAttribute("nickname", loginUser.getNickName());
            }
        }
        return "home";
    }

    // 회원가입
    @PostMapping("/signUp")
    public String join(@Valid @ModelAttribute JoinRequest joinRequest, BindingResult bindingResult) {
        // id 중복 체크
        if(userService.checkUserIDDuplicate(joinRequest.getUserID())) {
            bindingResult.addError(new FieldError("joinRequest", "userID", "이미 존재하는 아이디입니다."));
        }

        // 닉네임 중복 체크
        if(userService.checkNickNameDuplicate(joinRequest.getNickName())) {
            bindingResult.addError(new FieldError("joinRequest", "nickName","이미 존재하는 별명입니다."));
        }

        // password 검증
        if(!joinRequest.getUserPW().equals(joinRequest.getPwCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "signUp";
        }

        userService.join(joinRequest);
        return "redirect:/DayBridge";
    }

    // 로그인
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
}
