package com.example.DayBridge.service;

import com.example.DayBridge.domain.FormData;
import com.example.DayBridge.repository.FormDataRepository;
import io.github.flashvayne.chatgpt.dto.image.ImageFormat;
import io.github.flashvayne.chatgpt.dto.image.ImageSize;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {

    @Autowired
    private FormDataRepository formdataRepository;

    @Autowired
    private final ChatgptService chatgptService;

    public void saveFormData(Long userNo, String pointColor, String windowPosition, Integer windowNum, String essentialFurniture, String roomSize) {
        FormData formdata = new FormData();
        formdata.setUserNo(userNo);
        formdata.setPointColor(pointColor);
        formdata.setWindowPosition(windowPosition);
        formdata.setWindowNum(windowNum);
        formdata.setEssentialFurniture(essentialFurniture);
        formdata.setRoomSize(roomSize);
        formdataRepository.save(formdata);
    }

    // 단순히 이미지 생성후 리턴하는게 아니라 b64를 이미지로 변환해서 리턴하도록 수정
    public byte[] getImageResponse(String prompt, ImageSize large, ImageFormat base64) throws IOException {


        List<String> generateImage = chatgptService.imageGenerate(prompt,1,large ,base64);
        String image = generateImage.toString();

        byte[] imageByte = Base64.getDecoder().decode(image);
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(imageByte));

        // 이미지를 PNG 형식으로 변환
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", outputStream);
        byte[] pngImage = outputStream.toByteArray();

        return pngImage;
    }

    public void saveImage(byte[] imageBytes, String formatName) throws IOException {
        // 현재 시간을 기준으로 파일 이름 생성
        String fileName = "image_" + System.currentTimeMillis() + "." + formatName;
        // 경로 확인 필요
        File path = new File(ResourceUtils.getFile("classpath:").getAbsolutePath() + "/image");
        if (!path.exists()) {
            path.mkdirs(); // 폴더가 없다면 생성
        }
        File outputFile = new File(path, fileName);
        ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageBytes)), formatName, outputFile);
    }
    
}