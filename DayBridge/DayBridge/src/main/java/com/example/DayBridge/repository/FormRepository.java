package com.example.DayBridge.repository;

import com.example.DayBridge.model.Interior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {

    List<FormData> findByUserNo(Long userNo); // 사용자별 생성된 이미지 출력


}
