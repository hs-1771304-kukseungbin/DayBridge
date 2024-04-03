package com.example.DayBridge.service;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {
    //    chatGPT를 통해 사용자의 입력값을 바탕으로 prompt를 만들어 DALL-E에게 전달하는 것을 목표로 함
    @Resource(name = "getOpenAiService")
    private static OpenAiService openAiService;


    //chatGPT를 활용한 프롬프트 생성 로직


    //DALL-E를 통한 그림 생성
    public static String generatePicture(String prompt) {
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(prompt)
                .size("512x512")
                .n(1)
                .responseFormat("b64_json")
                .build();

        // url로 return 하지만 1시간 후 만료됨(b64_json return 필요) -> json형식으로 수정 완료
        String json = openAiService.createImage(createImageRequest).getData().get(0).getB64Json();
        return json;
    }
}
