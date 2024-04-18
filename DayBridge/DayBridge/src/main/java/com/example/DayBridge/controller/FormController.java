package com.example.DayBridge.controller;

import com.example.DayBridge.service.FormService;
import io.github.flashvayne.chatgpt.dto.image.ImageFormat;
import io.github.flashvayne.chatgpt.dto.image.ImageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;

@Controller
public class FormController {

    @Autowired
    private FormService formService;
    
    @RequestMapping("/DayBridge/form")
    public String showForm() {
        return "form"; // HTML 폼 페이지
    }

    @PostMapping("/DayBridge/submitForm")
    public String submitForm(@RequestParam("userNo") Long userNo,
                             @RequestParam("pointColor") String pointColor,
                             @RequestParam("windowPosition") String windowPosition,
                             @RequestParam("windowNum") Integer windowNum,
                             @RequestParam("essentialFurniture") String essentialFurniture,
                             @RequestParam("roomSize") String roomSize,
                             Model model) throws IOException {

        formService.saveFormData(userNo, pointColor, windowPosition, windowNum, essentialFurniture, roomSize);
        String dataToSend = pointColor + "색의 톤에, 가구는" + essentialFurniture + "가 있고, 창문이" + windowNum + "개 있는," + roomSize+"평의 방";

        // chatgpt
//        public void testImageList(){
//    List<String> images = chatgptService.imageGenerate("A cute baby sea otter", 2, ImageSize.SMALL, ImageFormat.URL);
//    System.out.print(images.toString());//["https://oaidalleapipr.....ZwA%3D","https://oaidalleapipr....RE0%3D"]
//} 이런식으로 사진 사이즈랑 json형식으로 받아올 수 있음

        // 여기서 리턴 받는건 이미지 파일
        byte[] image = formService.getImageResponse(dataToSend, ImageSize.LARGE, ImageFormat.BASE64);
        String b64_image = Base64.getEncoder().encodeToString(image);
        // 이부분 수정됨
//        <img src="data:image/jpg;base64,${image}" /> 이렇게 화면에 그냥 넣을 수 있음
//        visionAPI에는 jpg로 넣어줘야함
        model.addAttribute("image", b64_image);

        return "result"; // 결과 표시할 페이지
    }
}
