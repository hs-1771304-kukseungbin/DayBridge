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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class FormController {

    @Autowired
    private FormService formService;
    
    @RequestMapping("/DayBridge/form")
    public String showForm() {
        return "form"; // HTML 폼 페이지
    }

    @PostMapping("/DayBridge/form")
    public String submitForm(@RequestParam("pointColor") String pointColor,
                             @RequestParam("windowPosition") String windowPosition,
                             @RequestParam("windowNum") Integer windowNum,
                             @RequestParam("essentialFurniture") String essentialFurniture,
                             @RequestParam("roomSize") int roomSize,
                             Model model) throws IOException {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//        System.out.println("======================================"+userId);
//        Users users = userRepository.findByUserID(userId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자입니다."));
//
//        formService.saveFormData(users, pointColor, windowPosition, windowNum, essentialFurniture, roomSize);

        String dataToSend = "A photograph resembling a real-life room, featuring " + essentialFurniture + " furniture and " + windowNum + " windows positioned " + windowPosition + ", all in a " + pointColor + " tone. The room size is " + roomSize + " square meters.";

        // 여기서 리턴 받는건 이미지 파일
        byte[] image = formService.getImageResponse(dataToSend, ImageSize.LARGE, ImageFormat.BASE64);

        // 파일 이름 생성 (예: 현재 시간을 기반으로 함)
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".png";

        // 이미지 저장 함수 호출
        formService.saveImage(image, fileName);

        String b64_image = Base64.getEncoder().encodeToString(image);

        //OpneCV 호출 및 처리 부분

        model.addAttribute("image", b64_image);

        return "result"; // 결과 표시할 페이지
    }

    // 파이썬 코드 호출 -> 결과 이미지들을 result 페이지에 넣음
    @PostMapping("/DayBridge/runOpenCV")
    public String runOpenCV(Model model, @RequestParam("targetImage") String targetImage) {
        try {
            String pythonScriptPath = "./OpenCV/runOpenCV.py";
            String pythonExecutablePath = "python3";

            // resources/dataSet 폴더의 절대 경로를 구합니다.
            String datasetPath = Paths.get("src", "main", "resources", "dataSet").toAbsolutePath().toString();

            List<String> cmd = new ArrayList<>();
            cmd.add(pythonExecutablePath);
            cmd.add(pythonScriptPath);
            cmd.add(targetImage);
            cmd.add(datasetPath); // 데이터 셋 폴더 경로를 인자로 추가

            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Error occurred during execution, exit code: " + exitCode);
            }

            // 파이썬 스크립트의 출력(가장 유사한 이미지의 이름)을 모델에 추가
            model.addAttribute("similarImage", output.toString());

            // 처리 결과를 보여줄 뷰 이름을 반환
//            return "result";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "result";
    }

}
