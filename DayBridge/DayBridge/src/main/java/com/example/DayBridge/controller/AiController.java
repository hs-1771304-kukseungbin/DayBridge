package com.example.DayBridge.controller;

import com.example.DayBridge.service.AiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final AiService aiService;
    
    // 전반적으로 수정 필요함

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/image")
    public ResponseEntity<?> generateImage(@RequestBody String prompt) {
        return new ResponseEntity<>(AiService.generatePicture(prompt), HttpStatus.OK);
    }
}
