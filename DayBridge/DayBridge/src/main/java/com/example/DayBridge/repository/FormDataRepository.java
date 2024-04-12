package com.example.DayBridge.repository;

import com.example.DayBridge.domain.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {

    List<FormData> findByUserNo(Long userNo); // 사용자별 생성된 이미지 출력


}
