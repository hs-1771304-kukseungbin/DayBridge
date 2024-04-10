package com.example.DayBridge.service;

import com.example.DayBridge.model.FormData;
import com.example.DayBridge.repository.FormDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormDataRepository formdataRepository;

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
}