package com.example.DayBridge.controller;

import com.example.DayBridge.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @Autowired
    private FormService formService;

    @RequestMapping("/form")
    public String showForm() {
        return "form"; // HTML 폼 페이지
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestParam("userNo") Long userNo,
                             @RequestParam("pointColor") String pointColor,
                             @RequestParam("windowPosition") String windowPosition,
                             @RequestParam("windowNum") Integer windowNum,
                             @RequestParam("essentialFurniture") String essentialFurniture,
                             @RequestParam("roomSize") String roomSize,
                             Model model) {

        formService.saveFormData(userNo, pointColor, windowPosition, windowNum, essentialFurniture, roomSize);
        String dataToSend = pointColor + "색의 톤에, 가구는" + essentialFurniture + "가 있고, 창문이" + windowNum + "개 있는," + roomSize+"평의 방";

        // chatgpt

        model.addAttribute("dataToSend", dataToSend);

        return "result"; // 결과 표시할 페이지
    }
}
